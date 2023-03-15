package com.schoolnews.manage.application.utils;

import android.text.TextUtils;
import android.widget.EditText;

import com.schoolnews.manage.application.R;


/**
 * Created by 张国亮 on 2017/5/13.
 */

public class CheckHelper {

    public static  boolean checkPhoneNum(EditText editText) {

        /* 手机号检查
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188 
        联通：130、131、132、152、155、156、185、186 176
        电信：133、153、180、189、（1349卫通） 
        总结起来就是第一位必定为1，第二位必定为3或4或5或6或7或8或9，其他位置的可以为0-9 
        */
        String mobiles = editText.getText().toString().trim();
        String telRegex = "[1][3456789]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、4、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。  
        if (TextUtils.isEmpty(mobiles)) {
            ToastUtils.showShortToast(R.string.phone_not_right);
            return false;
        } else {
            boolean check =  mobiles.matches(telRegex);
            if(!check)
            {
                ToastUtils.showShortToast(R.string.phone_not_right);
            }
            return check;
        }
    }


    public static  boolean checkPhoneNumLogin(EditText editText) {

        /* 手机号检查
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188 
        联通：130、131、132、152、155、156、185、186 176
        电信：133、153、180、189、（1349卫通） 
        总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9 
        */
        String mobiles = editText.getText().toString().trim();
        String telRegex = "[1][3456789]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。  
        if (TextUtils.isEmpty(mobiles)) {
            ToastUtils.showShortToast(R.string.phone_not_empty);
            return false;
        } else {
            boolean check =  mobiles.matches(telRegex);
            if(!check)
            {
                ToastUtils.showShortToast(R.string.error_phone_pwd);
            }
            return check;
        }
    }

    public static  boolean checkPhoneNum(EditText editText,boolean showMessage) {

        /* 手机号检查
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188 
        联通：130、131、132、152、155、156、185、186 176
        电信：133、153、180、189、（1349卫通） 
        总结起来就是第一位必定为1，第二位必定为3或5或6或8，其他位置的可以为0-9 
        */
        String mobiles = editText.getText().toString().trim();
        String telRegex = "[1][3456789]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。  
        if (TextUtils.isEmpty(mobiles)) {
            if(showMessage)
            ToastUtils.showShortToast("请输入手机号");
            return false;
        } else {
            boolean check =  mobiles.matches(telRegex);
            if(!check)
            {
                if(showMessage)
                ToastUtils.showShortToast(R.string.phone_not_right);
            }
            return check;
        }
    }

    public static boolean checkCode(EditText editText) {

        // 验证码检查
        String code = editText.getText().toString().trim();
        if (!StringUtils.isEmpty(code) && code.length() == 6 ) {
            return true;
        }
        else if(StringUtils.isEmpty(code))
        {
            ToastUtils.showShortToast(R.string.code_empty);
        }
        else
        {
            ToastUtils.showShortToast(R.string.code_error);
        }
        return false;
    }

    public static boolean checkPassword(EditText editText) {

        // 密码检查
        String password = editText.getText().toString().trim();
        if (password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{6,16}")) {
            return true;
        }
        else
        {
            ToastUtils.showShortToast(R.string.pwd_error);
        }
        return false;
    }

    public static boolean checkUsername(EditText editText)
    {
        // 姓名
        String name = editText.getText().toString().trim();
        if (StringUtils.isEmpty(name)||name.matches("[\\u4e00-\\u9fa5_a-zA-Z\\s]{1,8}")) {
            return true;
        }
        else if(name.length()>8)
        {
            ToastUtils.showShortToast(R.string.error_name_length);
            return false;
        }
        else
        {
            ToastUtils.showShortToast(R.string.error_name);
            return false;
        }
    }

    public static boolean checkOldPassword(EditText editText)
    {
        // 旧密码
        String password = editText.getText().toString().trim();
        if (password.matches("(?!^[0-9]+$)(?!^[A-z]+$)(?!^[^A-z0-9]+$)^.{6,16}$")) {
            return true;
        }
        ToastUtils.showShortToast(R.string.error_old_pwd);
        return false;
    }

    public static boolean checkEmail(EditText editText)
    {
        // 邮箱
        String email = editText.getText().toString().trim();
        if (StringUtils.isEmpty(email) || email.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)" +
                "|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")) {
            return true;
        }
        else
        {
            ToastUtils.showShortToast(R.string.error_email);
            return false;
        }
    }
    public static boolean checkQQ(EditText editText)
    {
        // QQ
        String qq = editText.getText().toString().trim();
        if (StringUtils.isEmpty(qq) || qq.matches("[1-9][0-9]{4,14}")) {
            return true;
        }
        ToastUtils.showShortToast(R.string.error_qq);
        return false;
    }
    public static boolean checkIdfy(EditText editText)
    {
        // 身份证
        String idfy = editText.getText().toString().trim();
        if (StringUtils.isEmpty(idfy) || idfy.matches("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)")) {
            return true;
        }
        ToastUtils.showShortToast(R.string.error_idtify);
        return false;
    }
}
