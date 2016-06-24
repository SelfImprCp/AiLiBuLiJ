package com.anyin.ailibuli.ui;

import android.app.Dialog;
import android.view.View;
import android.widget.TextView;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.api.MyAPI;
import com.anyin.ailibuli.api.MyResponseHandler;
import com.anyin.ailibuli.base.BaseActivity;
import com.anyin.ailibuli.bean.User;
import com.anyin.ailibuli.cache.CacheManager;
import com.anyin.ailibuli.custom.TitleBarView;
import com.anyin.ailibuli.db.MyDbUtil;
import com.anyin.ailibuli.ui.dialog.BasicDialog;
import com.anyin.ailibuli.utils.FileUtil;
import com.anyin.ailibuli.utils.LogCp;
import com.anyin.ailibuli.utils.UIHelper;

import org.kymjs.kjframe.ui.BindView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jerry on 2016/6/24.
 */
public class TestActivity extends BaseActivity {


    private Dialog dialog;


    @BindView(id = R.id.test_title)
    private TitleBarView test_title;


    @BindView(id = R.id.db_test, click = true)
    private TextView db_test;


    @BindView(id = R.id.ache_test, click = true)
    private TextView ache_test;


    @BindView(id = R.id.https_test, click = true)
    private TextView https_test;


    @BindView(id = R.id.file_save_test, click = true)
    private TextView file_save_test;


    @BindView(id = R.id.file_read_test, click = true)
    private TextView file_read_test;


    @BindView(id = R.id.file_isExist_test, click = true)
    private TextView file_isExist_test;


    @BindView(id = R.id.file_delete_test, click = true)
    private TextView file_delete_test;


    @BindView(id = R.id.file_write_sdcare_file_test, click = true)
    private TextView file_write_sdcare_file_test;


    @BindView(id = R.id.file_read_sdcare_file_test, click = true)
    private TextView file_read_sdcare_file_test;


    @BindView(id = R.id.file_del_sdcare_dir_test, click = true)
    private TextView file_del_sdcare_dir_test;


    @BindView(id = R.id.file_sdcare_dir_test, click = true)
    private TextView file_sdcare_dir_test;

    @BindView(id = R.id.file_get_size, click = true)
    private TextView file_get_size;

    @BindView(id = R.id.file_get_iszai, click = true)
    private TextView file_get_iszai;


    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_test);
    }

    @Override
    protected void initView() {
        super.initView();

        test_title.setTitleStr("测试页面");
        test_title.setTitleBackClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.db_test:

                // 测试 DB

                // data file
                MyDbUtil myDbUtil = new MyDbUtil();

                User ugc = new User(); //warn: The ugc must have id field or @ID annotate


                ugc.setId(1);
                ugc.setNickname(" lcp  ");
                myDbUtil.getKJDB(this).save(ugc);


                ArrayList<User> user = (ArrayList<User>) myDbUtil.getKJDB(this).findAllByWhere(User.class, " id = 1");

                LogCp.i(LogCp.CP, TestActivity.class + "  数据 库查出来的， , " + user.get(0).getNickname());

                break;


            case R.id.ache_test:
                User userD = new User(); //warn: The ugc must have id field or @ID annotate


                userD.setId(1);
                userD.setNickname(" lcp ,,,  ");
                CacheManager.saveObject(this, userD, "textca");

                User user1 = (User) CacheManager.readObject(this, "textca");


                LogCp.i(LogCp.CP, TestActivity.class + "   cache ， , " + user1.getNickname());

                break;


            case R.id.https_test:


                dialog = BasicDialog.loadDialog(this, "提交中...")
                        .getDialog();
                dialog.show();


                MyAPI.Login("13725312913", "123456", new MyResponseHandler() {


                    @Override
                    public void dataSuccess(String res) {
                        LogCp.i(LogCp.CP, TestActivity.class + "返回 的 dd 0" + res);


                    }

                    @Override
                    public void dataFinish() {
                        dialog.dismiss();

                    }

                    @Override
                    public void dataFailuer(int errorNo, String strMsg) {
                        LogCp.i(LogCp.CP, TestActivity.class + " 加载出错 " + errorNo + " 错了，" + strMsg);


                    }


                });


                break;


            // 测试 写文件
            case R.id.file_save_test:

                FileUtil fileUtil = new FileUtil(this);
                try {
                    fileUtil.savePrivateDataDataPackageFiles("test", "测试文件的写入，");
                } catch (Exception e) {
                    e.printStackTrace();
                }


                break;


            case R.id.file_read_test:

                FileUtil fileUtilT = new FileUtil(this);

                try {
                    String readContent = fileUtilT.readDataDataPackageFiles("test");
                    UIHelper.showToast("  读取到的文件内容  " + readContent);
                    //   LogCp.i(LogCp.CP, PlanFragment.class + "  读取到的文件内容  " + readContent );


                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case R.id.file_isExist_test:
                FileUtil.isFileExist(FileUtil.DATA_DATA_FILE_PATH, "test");

                break;

            case R.id.file_delete_test:
                FileUtil.delelteFile(FileUtil.DATA_DATA_FILE_PATH, "test");

                break;

            //读取 sdcard 上的文件
            case R.id.file_read_sdcare_file_test:

                String string = FileUtil.readSDFile(FileUtil.SDPATH, "cp");

                LogCp.i(LogCp.CP, TestActivity.class + " 读到的内容" + string);

                break;
            // 写入 sd 文件
            case R.id.file_write_sdcare_file_test:

                FileUtil.saveContentToSDCard("这 内容 是写到sd卡上的", FileUtil.SDPATH, "cp");

                break;


            //删除sd目录
            case R.id.file_del_sdcare_dir_test:
                FileUtil.deleteDirectory("jl");
                break;

            // 创建sd目录
            case R.id.file_sdcare_dir_test:
                try {
                    boolean isSuccess = FileUtil.createDirectory("jl");
                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;

            case R.id.file_get_size:
                String strSize = FileUtil.formatFileSize(FileUtil.getFileSize(FileUtil.SDPATH, "cp"));

                LogCp.i(LogCp.CP, TestActivity.class + "  算出来的文件 大小， " + strSize);


                break;

            case R.id.file_get_iszai:


                boolean isSD = FileUtil.isFileExist(FileUtil.SDPATH, "cp");


                LogCp.i(LogCp.CP, TestActivity.class + "   SD 卡上的文件是否存在， " + isSD);

                boolean isDATA =       FileUtil.isFileExist(FileUtil.DATA_DATA_FILE_PATH, "test");

                LogCp.i(LogCp.CP, TestActivity.class + "   DATA  卡上的文件是否存在， " + isDATA);

                break;


        }
    }
}
