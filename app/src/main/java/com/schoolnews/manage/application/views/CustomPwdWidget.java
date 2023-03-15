package com.schoolnews.manage.application.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;

import com.schoolnews.manage.application.R;

/**
 * Created by Administrator on 2017/6/5.
 */
public class CustomPwdWidget extends android.support.v7.widget.AppCompatEditText {
    private PasswordFullListener mListener;
    // 画笔
    private Paint mPaint;
    //输入的数字是否隐藏
    private boolean mIsHide;
    // 一个密码所占的宽度
    private int mPasswordItemWidth;
    // 密码的个数默认为6位数
    private int mPasswordNumber = 6;
    // 背景边框颜色
    private int mBgColor = Color.parseColor("#d1d2d6");
    // 背景边框大小
    private int mBgSize = 1;
    // 背景边框圆角大小
    private int mBgCorner = 0;
    // 分割线的颜色
    private int mDivisionLineColor = mBgColor;
    // 分割线的大小
    private int mDivisionLineSize = 1;
    // 密码圆点的颜色
    private int mPasswordColor = mDivisionLineColor;
    // 密码圆点的半径大小
    private int mPasswordRadius = 4;

    public CustomPwdWidget(Context context) {
        this(context, null);
    }

    public CustomPwdWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initAttributeSet(context, attrs);
        // 设置输入模式是密码
        //setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        // 不显示光标
        setCursorVisible(false);

       /* 在你的Activity的onCreate()方法中加入以下代码：

        EditText edittext=(EditText )findViewById(R.id.xx);

        edittext.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);



        禁用系统软键盘方法2：

        在你的Activity的onCreate()方法中加入以下代码：

        EditText edittext=(EditText )findViewById(R.id.xx);

        edittext.setKeyListener(null);*/
    }

    /**
     * 初始化属性
     */
    private void initAttributeSet(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomPwdWidgt);
        //获取是否隐藏的属性,默认为不隐藏
        mIsHide = array.getBoolean(R.styleable.CustomPwdWidgt_isHide,false);

        //获取密码的位数
        mPasswordNumber = array.getInt(R.styleable.CustomPwdWidgt_passwordNumber,6);
        // 获取大小
        mDivisionLineSize = (int) array.getDimension(R.styleable.CustomPwdWidgt_divisionLineSize, dip2px(mDivisionLineSize));
        mPasswordRadius = (int) array.getDimension(R.styleable.CustomPwdWidgt_passwordRadius, dip2px(mPasswordRadius));
        mBgSize = (int) array.getDimension(R.styleable.CustomPwdWidgt_bgSize, dip2px(mBgSize));
        mBgCorner = (int) array.getDimension(R.styleable.CustomPwdWidgt_bgCorner, 0);
        // 获取颜色
        mBgColor = array.getColor(R.styleable.CustomPwdWidgt_bgColor, mBgColor);
        mDivisionLineColor = array.getColor(R.styleable.CustomPwdWidgt_divisionLineColor, mDivisionLineColor);
        mPasswordColor = array.getColor(R.styleable.CustomPwdWidgt_passwordColor, mDivisionLineColor);
        array.recycle();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    /**
     * dip 转 px
     */
    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dip, getResources().getDisplayMetrics());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int passwordWidth = getWidth() - (mPasswordNumber - 1) * mDivisionLineSize - mBgSize * 2;
        mPasswordItemWidth = passwordWidth / mPasswordNumber;   //每个密码框的宽度
        // 绘制背景
        drawBg(canvas);
        // 绘制分割线
        drawDivisionLine(canvas);
        // 绘制密码
        drawPassword(canvas);
    }

    /**
     * 绘制背景
     */
    private void drawBg(Canvas canvas) {
        mPaint.setColor(mBgColor);
        // 设置画笔为空心
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mBgSize);
        RectF rectF = new RectF(mBgSize/2, mBgSize/2, getWidth() - mBgSize/2, getHeight() - mBgSize/2);
        // 如果没有设置圆角，就画矩形
        if (mBgCorner == 0) {
            canvas.drawRect(rectF, mPaint);
        } else {
            // 如果有设置圆角就画圆矩形
            canvas.drawRoundRect(rectF, mBgCorner, mBgCorner, mPaint);
        }
    }

    /**
     * 绘制密码
     */
    private void drawPassword(Canvas canvas) {
        int passwordLength = getText().length();
        mPaint.setColor(mPasswordColor);
        // 设置画笔为实心
        mPaint.setStyle(Paint.Style.FILL);
        String password = getText().toString();
        for (int i = 0; i < passwordLength; i++) {
            int cx = i * mDivisionLineSize + i * mPasswordItemWidth + mPasswordItemWidth / 2 + mBgSize;
            //canvas.drawCircle(cx, getHeight() / 2, mPasswordRadius, mPaint);
            //
            //判断是否隐藏的属性，来绘制密码
            if(mIsHide){
                //隐藏密码，用圆圈代替
                canvas.drawCircle(cx, getHeight() / 2, mPasswordRadius, mPaint);
            }else{
                //不隐藏密码，并让数字居中显示
                mPaint.setTextSize(getHeight()/2);
                canvas.drawText(password.charAt(i)+"",cx, getHeight()/4 *3, mPaint);
            }
        }
    }

    /**
     * 绘制分割线
     */
    private void drawDivisionLine(Canvas canvas) {
        mPaint.setStrokeWidth(mDivisionLineSize);
        mPaint.setColor(mDivisionLineColor);
        for (int i = 0; i < mPasswordNumber - 1; i++) {
            //int startX = (i + 1) * mDivisionLineSize + (i + 1) * mPasswordItemWidth + mBgSize;
            int startX = (i)*mDivisionLineSize+(i+1)*mPasswordItemWidth+mBgSize+mDivisionLineSize/2;
            canvas.drawLine(startX, mBgSize, startX, getHeight() - mBgSize, mPaint);
        }
    }


    /**
     * 添加密码
     * @param number
     */
    public void addPassword(String number) {
        number = getText().toString().trim() + number;
        setText(number);
        //当输入的字符数等于或超过设置的字符输入个数时，会回调设置的监听方法
        if (number.length() >= mPasswordNumber) {
            mListener.passwordFullListener(number);
            return;
        }
    }

    /**
     * 删除最后一位密码
     */
    public void deleteLastPassword() {
        String currentText = getText().toString().trim();
        if (TextUtils.isEmpty(currentText)) {
            return;
        }
        currentText = currentText.substring(0, currentText.length() - 1);
        setText(currentText);
    }

    /**
     * 字符填满的监听接口
     */
    public interface PasswordFullListener {
        public void passwordFullListener(String number);
    }

    /**
     * 给输入框添加填满的监听事件
     * @param listener
     */
    public void setPasswordFullListener(PasswordFullListener listener){
        this.mListener = listener;
    }
}

/*/输入类型为没有指定明确的类型的特殊内容类型
        editText.setInputType(InputType.TYPE_NULL);

        //输入类型为普通文本
        editText.setInputType(InputType.TYPE_CLASS_TEXT);

        //输入类型为数字文本
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);

        //输入类型为电话号码
        editText.setInputType(InputType.TYPE_CLASS_PHONE);

        //输入类型为日期和时间
        editText.setInputType(InputType.TYPE_CLASS_DATETIME);

        //输入类型为{@link#TYPE_CLASS_DATETIME}的缺省变化值，允许输入日期和时间。
        editText.setInputType(InputType.TYPE_DATETIME_VARIATION_NORMAL);

        //输入类型为{@link#TYPE_CLASS_DATETIME}的缺省变化值，只允许输入一个日期。
        editText.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);

        //输入类型为{@link#TYPE_CLASS_DATETIME}的缺省变化值，只允许输入一个时间。
        editText.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME);

        //输入类型为决定所给文本整体类的位掩码
        editText.setInputType(InputType.TYPE_MASK_CLASS);

        //输入类型为提供附加标志位选项的位掩码
        editText.setInputType(InputType.TYPE_MASK_FLAGS);

        //输入类型为决定基类内容变化的位掩码
        editText.setInputType(InputType.TYPE_MASK_VARIATION);

        //输入类型为小数数字，允许十进制小数点提供分数值。
        editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        //输入类型为数字是带符号的，允许在开头带正号或者负号
        editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);

        //输入类型为{@link#TYPE_CLASS_NUMBER}的缺省变化值：为纯普通数字文本
        editText.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);

        //输入类型为{@link#TYPE_CLASS_NUMBER}的缺省变化值：为数字密码
        editText.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        //输入类型为自动完成文本类型
        editText.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);

        //输入类型为自动纠正文本类型
        editText.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);

        //输入类型为所有字符大写
        editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

        //输入类型为每句的第一个字符大写
        editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        //输入类型为每个单词的第一个字母大写
        editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);

        //输入多行文本
        editText.setInputType(InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE);

        //进行输入时，输入法无提示
        editText.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        //输入一个短的，可能是非正式的消息，如即时消息或短信。
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE);

        //输入长内容，可能是正式的消息内容，比如电子邮件的主体
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);

        //输入文本以过滤列表等内容
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_FILTER);

        //输入一个电子邮件地址
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        //输入电子邮件主题行
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT);

        //输入一个密码
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);

        //输入老式的普通文本
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);

        //输入人名
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);

        //输入邮寄地址
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);

        //输入语音发音输入文本，如联系人拼音名称字段
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_PHONETIC);

        //输入URI
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_URI);

        //输入对用户可见的密码
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

        //输入网页表单中的文本
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT);

        //输入网页表单中的邮件地址
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);

        //输入网页表单中的密码
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);*/
