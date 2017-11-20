package com.ah.dagbow.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ah.dagbow.Holder.IconTreeItemHolder;
import com.ah.dagbow.Holder.ProfileHolder;
import com.ah.dagbow.Holder.SelectableHeaderHolder;
import com.ah.dagbow.Holder.SelectableItemHolder;
import com.ah.dagbow.R;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;


/**
 * Created by Hamid Sani.
 * A lot of the code is taken from Bogdan Melnychuk
 */
public class OverviewFragment extends Fragment {
    private AndroidTreeView tView;
    private boolean selectionModeEnabled = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.overview, null, false);
        ViewGroup containerView = rootView.findViewById(R.id.container);

        TreeNode root = TreeNode.root();

        TreeNode s1 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_sd_storage, "Storage1")).setViewHolder(new ProfileHolder(getActivity()));
        TreeNode s2 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_sd_storage, "Storage2")).setViewHolder(new ProfileHolder(getActivity()));
        s1.setSelectable(false);
        s2.setSelectable(false);

        TreeNode folder1 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_folder, "Folder 1")).setViewHolder(new SelectableHeaderHolder(getActivity()));
        TreeNode folder2 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_folder, "Folder 2")).setViewHolder(new SelectableHeaderHolder(getActivity()));
        TreeNode folder3 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_folder, "Folder 3")).setViewHolder(new SelectableHeaderHolder(getActivity()));

        fillFolder(folder1);
        fillFolder(folder2);
        fillFolder(folder3);

        s1.addChildren(folder1, folder2);
        s2.addChildren(folder3);

        root.addChildren(s1, s2);

        tView = new AndroidTreeView(getActivity(), root);
        tView.setDefaultAnimation(true);
        containerView.addView(tView.getView());

        if (savedInstanceState != null) {
            String state = savedInstanceState.getString("tState");
            if (!TextUtils.isEmpty(state)) {
                tView.restoreState(state);
            }
        }
        return rootView;
    }

    private void fillFolder(TreeNode folder) {
        TreeNode file1 = new TreeNode("File1").setViewHolder(new SelectableItemHolder(getActivity()));
        TreeNode file2 = new TreeNode("File2").setViewHolder(new SelectableItemHolder(getActivity()));
        TreeNode file3 = new TreeNode("File3").setViewHolder(new SelectableItemHolder(getActivity()));
        folder.addChildren(file1, file2, file3);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tState", tView.getSaveState());
    }
}
