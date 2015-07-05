package com.juankysoriano.materiallife.editor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juankysoriano.materiallife.R;
import com.juankysoriano.materiallife.WorldRetriever;
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
        worldEditor = new WorldEditor(WorldRetriever.INSTANCE.getWorld());
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

    private PixelButton.OnClickListener onClearClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editorMenuView.animateClear(clearAnimationListener);
        }
    };

    private PixelButton.OnClickListener onCancelClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            worldEditor.cancel();
            worldEditor.endEdition();
            if (hasActionSelectedListener()) {
                onActionSelectedListener.onActionPerformed(EditorAction.CANCEL);
            }
        }
    };

    private PixelButton.OnClickListener onDoneClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            worldEditor.endEdition();
            if (hasActionSelectedListener()) {
                onActionSelectedListener.onActionPerformed(EditorAction.DONE);
            }
        }
    };

    private EditorMenuView.ClearAnimationListener clearAnimationListener = new EditorMenuView.ClearAnimationListener() {
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
