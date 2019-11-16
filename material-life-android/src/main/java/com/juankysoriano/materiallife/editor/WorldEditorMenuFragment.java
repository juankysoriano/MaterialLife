package com.juankysoriano.materiallife.editor;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.ui.sketch.PixelButton;
import com.juankysoriano.materiallife.ui.sketch.editor.EditorMenuView;
import com.novoda.notils.caster.Views;

public class WorldEditorMenuFragment extends Fragment implements WorldEditorMenu {
    public static final String TAG = "WorldEditorMenuFragment";

    private WorldEditor worldEditor;
    private EditorMenuView editorMenuView;
    private PixelButton clearButton;
    private PixelButton cancelButton;
    private PixelButton doneButton;
    private EditorMenuView.OnActionSelectedListener onActionSelectedListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.include_edit_view, container, false);
        editorMenuView = Views.findById(fragmentView, R.id.edit_layout);
        clearButton = Views.findById(fragmentView, R.id.edit_fab_clear);
        cancelButton = Views.findById(fragmentView, R.id.edit_fab_cancel);
        doneButton = Views.findById(fragmentView, R.id.edit_fab_accept);
        worldEditor = WorldEditor.newInstance();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attachListeners();
        worldEditor.startEdition();
    }

    private void attachListeners() {
        clearButton.setOnClickListener(onClearClickListener);
        cancelButton.setOnClickListener(onCancelClickListener);
        doneButton.setOnClickListener(onDoneClickListener);
    }

    private final PixelButton.OnClickListener onClearClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editorMenuView.animateClear(clearAnimationListener);
        }
    };

    private final PixelButton.OnClickListener onCancelClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            worldEditor.cancel();
            worldEditor.endEdition();
            if (hasActionSelectedListener()) {
                onActionSelectedListener.onActionPerformed(EditorAction.CANCEL);
            }
        }
    };

    private final PixelButton.OnClickListener onDoneClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            worldEditor.endEdition();
            if (hasActionSelectedListener()) {
                onActionSelectedListener.onActionPerformed(EditorAction.DONE);
            }
        }
    };

    private final EditorMenuView.ClearAnimationListener clearAnimationListener = new EditorMenuView.ClearAnimationListener() {
        @Override
        public void onClearDone() {
            worldEditor.clear();
            if (hasActionSelectedListener()) {
                onActionSelectedListener.onActionPerformed(EditorAction.CLEAR);
            }
        }
    };

    @Override
    public void attachActionSelectedListener(EditorMenuView.OnActionSelectedListener listener) {
        onActionSelectedListener = listener;
    }

    @Override
    public void detachActionSelectedListener() {
        onActionSelectedListener = null;
    }

    private boolean hasActionSelectedListener() {
        return onActionSelectedListener != null;
    }

    @Override
    public WorldEditorMenuFragment getFragment() {
        return this;
    }

    @Override
    public void abortEdition() {
        worldEditor.cancel();
    }

    private void detachListeners() {
        clearButton.setOnClickListener(null);
        cancelButton.setOnClickListener(null);
        doneButton.setOnClickListener(null);
    }

    @Override
    public void onDestroyView() {
        detachListeners();
        super.onDestroyView();
    }
}
