package com.example.appskeleton.view.activity;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appskeleton.R;
import com.example.appskeleton.bean.json.UserBean;
import com.example.appskeleton.model.util.SharedPrefrenceUtils;
import com.example.appskeleton.presenter.PresenterLogin;
import com.example.appskeleton.util.LogUtils;
import com.example.appskeleton.util.ToastUtil;
import com.example.appskeleton.view.base.BaseActivity;
import com.example.appskeleton.view.iview.IViewLogin;
import com.example.appskeleton.view.util.ProgressDialogUtil;
import com.example.appskeleton.view.widget.ProEditText;


/**
 * Login screen
 *
 */
public class LoginActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor>,IViewLogin<UserBean> {
    private static final String TAG = "LoginActivity";
    public static final int REQUEST_CODE_SETNICK = 1;

    ImageView imgBack;

    TextView txtTitle;
    private AutoCompleteTextView usernameEditText;
    private ProEditText passwordEditText;
    private CheckBox rememberPassCB;

    private boolean progressShow;
    private boolean autoLogin = false;
    private String currentUsername;
    private String currentPassword;
    private ProgressDialog pd;
    TextView forgotTv;
    TextView register;
    private PresenterLogin mPresenter;
    private ProgressDialogUtil progressDialogUtil;
    private boolean rightPicForETClick = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tag = getLocalClassName();

        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);
        imgBack = (ImageView) findViewById(R.id.toolbar_back);
        txtTitle  = (TextView) findViewById(R.id.toolbar_title);
        txtTitle.setText("登录");
        imgBack.setVisibility(View.VISIBLE);
        txtTitle.setVisibility(View.VISIBLE);
        forgotTv = (TextView) findViewById(R.id.login_error_tv);
        register = (TextView) findViewById(R.id.toRegister);
        forgotTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        register.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        mPresenter = new PresenterLogin(this);
        progressDialogUtil = new ProgressDialogUtil(mContext);
        /*// enter the main activity if already logged in
        if (SuperWeChatHelper.getInstance().isLoggedIn()) {
            autoLogin = true;
            startActivity(new Intent(LoginActivity.this, MainActivity.class));

            return;
        }*/

        usernameEditText = (AutoCompleteTextView) findViewById(R.id.username);
        passwordEditText = (ProEditText) findViewById(R.id.password);
        rememberPassCB = (CheckBox) findViewById(R.id.rememberpass_cb);

        // if user changed, clear the password
        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordEditText.setText(null);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN))) {
                    login(null);
                    return true;
                } else {
                    return false;
                }
            }
        });
        Drawable[] drawables = passwordEditText.getCompoundDrawables();
        final Drawable greyEyeDrawable = getResources().getDrawable(R.drawable.grey_eye);
        greyEyeDrawable.setBounds(drawables[2].getBounds());//这一步不能省略
        final Drawable eyeDrawable = getResources().getDrawable(R.drawable.eye);
        eyeDrawable.setBounds(drawables[2].getBounds());
        passwordEditText.setRightPicOnclickListener(new ProEditText.RightPicOnclickListener() {
            @Override
            public void rightPicClick() {
                rightPicForETClick = !rightPicForETClick;
                if (rightPicForETClick) {
                    passwordEditText.setCompoundDrawables(null, null,greyEyeDrawable , null);
                    passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());//明文
                }
                else {
                    passwordEditText.setCompoundDrawables(null, null,eyeDrawable , null);
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());//密文
                }
            }
        });

        rememberPassCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) SharedPrefrenceUtils.getInstance().setRememberPassword(true);
                else SharedPrefrenceUtils.getInstance().setRememberPassword(false);
            }
        });
        populateAutoComplete();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (!TextUtils.isEmpty(SharedPrefrenceUtils.getInstance().getUsername()))
            usernameEditText.setText(SharedPrefrenceUtils.getInstance().getUsername());
        if (SharedPrefrenceUtils.getInstance().isRememberPassword()) {
            passwordEditText.setText(SharedPrefrenceUtils.getInstance().getPassword() + "");
            rememberPassCB.setChecked(true);
        }
    }


    private void populateAutoComplete() {


        if (Build.VERSION.SDK_INT >= 14) {
            // Use ContactsContract.Profile (API 14+)
            getLoaderManager().initLoader(0, null, this);
        }
    }

    /**
     * login
     *
     * @param view
     */
    public void login(View view) {
//        if (!EaseCommonUtils.isNetWorkConnected(this)) {
//            Toast.makeText(this, R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
//            return;
//        }
        currentUsername = usernameEditText.getText().toString().trim();
        currentPassword = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(currentUsername)) {
            Toast.makeText(this, R.string.User_name_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(currentPassword)) {
            Toast.makeText(this, R.string.Password_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }


        progressDialogUtil.createLoadingProgressDialog("提示","正在登录...");
        try {
            mPresenter.login(mContext,currentUsername,currentPassword);
        } catch (Exception e) {
//            progressDialogUtil.dismissDialog();
            LogUtils.i(tag,"login "+e.getMessage());
            ToastUtil.showShortToast("登陆异常 "+e.getMessage());
            e.printStackTrace();
        }

//        pd = new ProgressDialog(LoginActivity.this);
//        pd.setCanceledOnTouchOutside(false);
//        pd.setOnCancelListener(new OnCancelListener() {
//
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                Log.d(TAG, "EMClient.getInstance().onCancel");
//                progressShow = false;
//            }
//        });
//        pd.setMessage("登录中");
//        pd.show();


    }

    //都登录成功后的环信配置
    private void logonForSet() {


        if (!LoginActivity.this.isFinishing() && pd.isShowing()) {
            pd.dismiss();
        }

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    /*private void loginToAppServer() {
=======
        SuperWeChatHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();
    }

    private void loginToAppServer() {
>>>>>>> origin/master
        model = new UserModel();
        model.login(this, currentUsername, MD5.getMessageDigest(currentPassword), new OnCompleteListener<String>() {
            @Override
            public void onSuccess(String result) {
                L.e(TAG, result);
                handleJson(result);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void handleJson(String s) {
        if (s != null) {
            Result result = ResultUtils.getResultFromJson(s, User.class);
            if (result != null) {
                Log.e(TAG, result.toString());
                if (result.getRetCode() == I.MSG_LOGIN_UNKNOW_USER) {
                    usernameEditText.requestFocus();
                    usernameEditText.setError(getString(R.string.login_unkown_user));
                } else if (result.getRetCode() == I.MSG_LOGIN_ERROR_PASSWORD) {
                    passwordEditText.requestFocus();
                    passwordEditText.setError(getString(R.string.login_password_error));
                } else {
                    loginSuccess();
                }
            }

        }
    }

    private void loginSuccess() {
        logonForSet();
        MFGT.gotoMain(this);
        MFGT.finish(this);
    }*/


    /**
     * register
     *
     * @param view
     */
    public void register(View view) {

//        startActivityForResult(new Intent(this, RegisterActivity.class), 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (autoLogin) {
            return;
        }
    }


    @Override
    public void showResult(UserBean o,int flag) {
        LogUtils.i(tag,o.toString());
        progressDialogUtil.dismissDialog();
        if (o.getCode() == 200 && o.getMessage() .equals("登陆成功") ){
            ToastUtil.showShortToast("登陆成功");
            SharedPrefrenceUtils.getInstance().setLoginState(true);
            SharedPrefrenceUtils.getInstance().setUsername(usernameEditText.getText().toString().trim());
            finish();
        }else {
//            ToastUtil.showShortToast("登录失败 "+o.getMessage());
//            LogUtils.i(tag,"showResult ","登录失败 "+o.getMessage());
        }
    }

    @Override
    public void showError(String error) {
        progressDialogUtil.dismissDialog();
        ToastUtil.showShortToast("登录失败 "+error);
        LogUtils.i(tag,"showError ","登录失败 "+error);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(tag,"onDestroy");
        SharedPrefrenceUtils.getInstance().setUsername(usernameEditText.getText().toString().trim());
        if (SharedPrefrenceUtils.getInstance().isRememberPassword()) SharedPrefrenceUtils.getInstance().setPassword(passwordEditText.getText().toString().trim());
    }

    @Override
    public void onBack() {
        super.onBack();
        LogUtils.i(tag,"onBack");
    }


}
