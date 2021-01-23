package com.ductho.nguphaptienganh.Frm;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ductho.nguphaptienganh.Activity.MainActivity;
import com.ductho.nguphaptienganh.R;
import com.ductho.nguphaptienganh.databinding.FragmentFrmChangeInfoBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;


public class FrmChangeInfo extends Fragment implements EasyPermissions.PermissionCallbacks {
    private static final int REQUEST_CODE_CAMERA = 123;
    private static final int REQUEST_CODE_GALLERY = 234;
    FragmentFrmChangeInfoBinding mBinding;
    String mTitle;
    FirebaseUser user;
    String newName;

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        MainActivity.mBubbleNavigationLinearView.setVisibility(View.VISIBLE);
//        MainActivity.mToolbar.setVisibility(View.VISIBLE);
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        user = FirebaseAuth.getInstance().getCurrentUser();
        Bundle extra = getArguments();
        mTitle = extra.getString(FrmAccount.KEY_TITLE);
        mBinding.tvTitle.setText(mTitle);
        mBinding.edtNew.setHint("Nhập " + mTitle.substring(4) + " mới");
        mBinding.btnUpdate.setOnClickListener(v -> {
            newName = mBinding.edtNew.getText().toString().trim();
            if (newName.isEmpty()) {
                Toast.makeText(getActivity(), "Hãy nhập " + mTitle.substring(4) + " mới", Toast.LENGTH_SHORT).show();
                return;
            } else if (user != null) {
                switch (mTitle) {
                    case "Đổi tên":
                        updateName(newName);
                        break;
                    case "Đổi email":
                        popUpConfirmDialog(0);
                        //https://firebase.google.com/docs/auth/android/manage-users#re-authenticate_a_user
                        break;
                    case "Đổi avatar":
//                        updateAvatar(newName);
                        break;
                    case "Đổi password":
                        popUpConfirmDialog(1);
                        break;
                }


            }
        });


        mBinding.btnTakePicture.setOnClickListener(view -> {
            Log.d("ABC", "Clicked");
            openCamera();
        });

        mBinding.btnPickPicture.setOnClickListener(view -> {
            openGallery();
        });
    }

    @AfterPermissionGranted(REQUEST_CODE_GALLERY)
    private void openGallery() {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(getActivity(), perms)) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_CODE_GALLERY);
        } else {
            EasyPermissions.requestPermissions(this,
                    "Cấp quyền truy cập gallery",
                    REQUEST_CODE_GALLERY,
                    perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(REQUEST_CODE_CAMERA)
    public void openCamera() {
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(Objects.requireNonNull(getActivity()), perms)) {
            // Already have permission, do the thing
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CODE_CAMERA);
        } else {
            // Do not have permissions, request them now
            Log.d("ABC", "request permission");
            EasyPermissions.requestPermissions(this,
                    "Cấp quyền truy cập camera",
                    REQUEST_CODE_CAMERA,
                    perms);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.d("ABC", "Permission denied");
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            mBinding.ivAvatar.setImageBitmap(bitmap);
        }

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                mBinding.ivAvatar.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    private void popUpConfirmDialog(int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View promptView = layoutInflater.inflate(R.layout.confirm_info_dialog, null);
        AlertDialog.Builder confirmDialogBuilder =
                new AlertDialog.Builder(getActivity(), R.style.RoundedCornersDialog);
        confirmDialogBuilder.setView(promptView);
        AlertDialog confirmDialog = confirmDialogBuilder.show();
        Button btnConfirm = promptView.findViewById(R.id.btn_dialog_yes);
        Button btnCancel = promptView.findViewById(R.id.btn_dialog_no);
        EditText edtEmail = promptView.findViewById(R.id.edtEmail);
        EditText edtPassword = promptView.findViewById(R.id.edtPassword);

        btnConfirm.setOnClickListener(view -> {
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getActivity(), "Hãy nhập thông tin xác nhận!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                if (i == 0) {
                    updateEmail(newName, email, password);
                } else {
                    updatePassword(newName, email, password);
                }
                confirmDialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(view -> {
            confirmDialog.dismiss();
        });

    }

    private void updatePassword(String newPassword, String email, String password) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = EmailAuthProvider
                .getCredential(email, password);

// Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("ABC", "User re-authenticated.");
                        currentUser.updatePassword(newPassword)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getActivity(), "Cập nhật password thành công", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            Log.d("ABC", "Error: " + task.getException().getMessage() + email);
                                        }
                                    }
                                });
                    }
                });
    }

    private void updateEmail(String newEmail, String email, String password) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = EmailAuthProvider
                .getCredential(email, password);

// Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("ABC", "User re-authenticated.");
                        currentUser.updateEmail(newEmail)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getActivity(), "Cập nhật email thành công", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });
    }

    private void updateName(String newName) {
        UserProfileChangeRequest profileUpdates =
                new UserProfileChangeRequest.Builder().setDisplayName(newName).build();
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentFrmChangeInfoBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }


}