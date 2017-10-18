package bwie.com.jdmodel;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import Api.MyApi;
import Bean.User_xq_Bean;
import Presenter.User_xq_Presenter;
import View.User_xq_View;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserResActivity extends AppCompatActivity implements User_xq_View, View.OnClickListener {
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    private RelativeLayout rl_icon;
    private RelativeLayout rl_nc;
    private TextView tv;
    private User_xq_Presenter uxp;
    private TextView tv_nc;
    private SharedPreferences sp;

    private BufferedOutputStream bos;
    private String tel;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_res);
        sp = getSharedPreferences("UID", MODE_PRIVATE);
        initView();
        initData();

    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.user_icon);
        tv = (TextView) findViewById(R.id.tv_yhm);
        rl_icon = (RelativeLayout) findViewById(R.id.rl_icon);
        rl_nc = (RelativeLayout) findViewById(R.id.rl_nc);
        tv_nc = (TextView) findViewById(R.id.tv_nc);
        rl_icon.setOnClickListener(this);
        rl_nc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(UserResActivity.this, NiChengActivity.class);
                startActivity(in);
            }
        });

    }

    private void initData() {
        uxp = new User_xq_Presenter(this);
        uxp.UserLogin(188 + "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getSharedPreferences("USERNAME", MODE_PRIVATE).getString("name", null) != null) {
            tv_nc.setText(getSharedPreferences("USERNAME", MODE_PRIVATE).getString("name", null));
        }
    }

    @Override
    public void UserSeccuss(final User_xq_Bean user, String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText(user.getData().getMobile());
                tv_nc.setText(user.getData().getNickname());
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("uid", user.getData().getUid() + "");
                edit.commit();
            }
        });

    }

    @Override
    public void UserFaile(String code) {

    }

    @Override
    public void NetFail(Call call, Exception e) {

    }

    @Override
    public void onClick(View view) {
        initPic();
    }

    public void initPic() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data);
                    }
                    break;
            }
        }
    }

    private void setFile(Bitmap photo) {
        File file = new File("mnt/sdcard/muzi.jpg");
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void setImageToView(Intent data) {

        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            iv.setImageBitmap(photo);
            setFile(photo);
            File file = new File("mnt/sdcard/muzi.jpg");

            OkHttpClient okHttpClient = new OkHttpClient();
            MultipartBody.Builder mb = new MultipartBody.Builder().setType(MultipartBody.FORM);
            mb.addFormDataPart("uid", 188 + "");
            mb.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("application/ortet-stream"), file));
            Request request = new Request.Builder().url(MyApi.PIC_URL)
                    .post(mb.build())
                    .build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(UserResActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    System.out.println("123456"+string);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(UserResActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });

        }
    }

    protected void startPhotoZoom(Uri uri) {

        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }
}
