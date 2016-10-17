package com.bignerdranch.android.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by alepena01 on 9/11/16.
 */
public class DetailDisplayFragment extends DialogFragment {

    private static final String EXTRA_PHOTO = "photo";

    private ImageView mDialogPicture;

    //new Instance
    public static DetailDisplayFragment newInstance(String photoPath) {
        Bundle args = new Bundle();
        args.putString(EXTRA_PHOTO, photoPath);

        DetailDisplayFragment fragment = new DetailDisplayFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String photoPath = getArguments().getString(EXTRA_PHOTO);

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_picture, null);

        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay()
                .getSize(size);

        mDialogPicture = (ImageView) v.findViewById(R.id.dialog_picture);
        Bitmap bitmapPhoto = PictureUtils.getScaledBitmap(photoPath, size.x, size.y / 2);
        mDialogPicture.setImageBitmap(bitmapPhoto);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();
    }
}
