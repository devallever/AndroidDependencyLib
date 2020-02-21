package com.allever.lib.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AutoLayout extends ViewGroup {

    private int mPaddingLeft;
    private int mPaddingTop;
    private int mPaddingRight;
    private int mPaddingBottom;

    //计算后要显示行的数据的集合
    private List<List<View>> mLines = new ArrayList<>();

    public AutoLayout(Context context) {
        this(context, null);
    }

    public AutoLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }


    //标准写法
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        mLines.clear();
//
//        //自身的padding
//        mPaddingTop = getPaddingTop();
//        mPaddingLeft = getPaddingLeft();
//        mPaddingRight = getPaddingRight();
//        mPaddingBottom = getPaddingBottom();
//
//        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
//        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);
//        int widthMeasureSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMeasureSize = MeasureSpec.getSize(heightMeasureSpec);
//
//        //通过计算获取的总行高(包括ViewGroup的padding和子View的margin)
//        int totalHeight = 0;
//        //最宽行的宽度
//        int currentMaxWidth = 0;
//        //当前行已占用的宽度
//        int lineUsedWidth = 0;
//        //计算时当前行的最大高度
//        int currentMaxHeight = 0;
//
//        //每一行中View的数据集
//        ArrayList<View> lineInfo = new ArrayList<>();
//
//        //获取子View的个数
//        int childCount = getChildCount();
//
//        //遍历子View对其进行测算
//        for (int i = 0; i < childCount; i++) {
//            View childView = getChildAt(i);
//
//            //判断子View的显示状态 gone就不进行测算
//            if (childView.getVisibility() == GONE) {
//                continue;
//            }
//
//            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
//
//            MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();
//
//            int childWidth = 0;
//            int childHeight = 0;
//
//            //第一个添加父布局的paddingLeft
//            if (0 == i) {
//                childWidth += mPaddingLeft;
//            }
//
//            //获取view的测量宽度
//            childWidth += childView.getMeasuredWidth() + (layoutParams.leftMargin + layoutParams.rightMargin);
//
//            //当前的行高
//            childHeight = childHeight + (childView.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin);
//
//            //当前行放不下时，重起一行显示
//
//            if (lineUsedWidth + childWidth > widthMeasureSize - mPaddingRight) {
//                //初始当前行的宽度
//                lineUsedWidth = childWidth + mPaddingLeft;
//                //添加一次行高
//                totalHeight += currentMaxHeight;
//                //初始化行高
//                currentMaxHeight = childHeight;
//                mLines.add(lineInfo);
//                lineInfo = new ArrayList<>();
//                lineInfo.add(childView);
//            } else {//当前行可以显示时
//                lineInfo.add(childView);
//                //增加当前行已显示的宽度
//                lineUsedWidth += childWidth;
//                //为了显示最大的行高
//                currentMaxHeight = Math.max(currentMaxHeight, childHeight);
//                //显示中最大的行宽
//                currentMaxWidth = Math.max(currentMaxWidth, lineUsedWidth);
//            }
//        }
//        mLines.add(lineInfo);
//        totalHeight += (mPaddingTop + mPaddingBottom + currentMaxHeight);
//        setMeasuredDimension(
//                (widthMeasureMode == MeasureSpec.EXACTLY) ? widthMeasureSize : currentMaxWidth + mPaddingRight,
//                (heightMeasureMode == MeasureSpec.EXACTLY) ? heightMeasureSize : totalHeight
//        );
//    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mLines.clear();

        mPaddingLeft = getPaddingLeft();
        mPaddingTop = getPaddingTop();
        mPaddingRight = getPaddingRight();
        mPaddingBottom = getPaddingBottom();

        //可用宽度
        int availableWidth = MeasureSpec.getSize(widthMeasureSpec);

        //总高度 = 自身padding(top和bottom) + 子View + 子View的margin(top和bottom)
        int totalHeight = 0;
        //当前最大行高
        int currentMaxHeight = 0;
        //当前最大的行宽
        int currentMaxWidth = 0;
        //当前行已使用的宽度
        int lineUsedWidth = 0;

        //子View个数
        int childCount = getChildCount();

        //每一行中View的数据集
        List<View> lineInfo = new ArrayList<>();

        //遍历每个子View进行测量自身，并保存
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);

            //不计算隐藏的子View
            if (childView.getVisibility() == GONE) {
                continue;
            }

            //测量子View
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();

            //当前子View所占用的宽度
            int childWidth = 0;
            //子View的高度
            int childHeight = 0;

            //如果是第一个需要加上父布局的paddingLeft
            if (i == 0) {
                childWidth += mPaddingLeft;
            }
            //当前子View的所占用的宽度 = [paddingLeft] + 子View本身宽度 + margin
            childWidth = childWidth + childView.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;

            //当前子View(或者说当前行)的高度 = 子View的高度 + margin
            childHeight = childHeight + childView.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;


            if (lineUsedWidth + childWidth > availableWidth - mPaddingRight) {
                //换行

                //下一行已使用宽度 要加上paddingLeft
                lineUsedWidth = childWidth + mPaddingLeft;
                //添加一次行高
                totalHeight = totalHeight + currentMaxHeight;
                currentMaxHeight = childHeight;

                mLines.add(lineInfo);
                lineInfo = new ArrayList<>();
                lineInfo.add(childView);
            } else {
                //不换行

                //增加当前行已显示的宽度
                lineUsedWidth += childWidth;
                //为了显示最大的行高
                currentMaxHeight = Math.max(childHeight, currentMaxHeight);
                //显示中最大的行宽
                currentMaxWidth = Math.max(childWidth, lineUsedWidth);

                lineInfo.add(childView);
            }
        }

        //添加最后一行的View
        mLines.add(lineInfo);
        //加上最后一行的高度和padding
        totalHeight = totalHeight + currentMaxHeight + mPaddingTop + mPaddingBottom;

        int resultWidth = 0;
        int resultHeight = 0;

        int selfWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int selfWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int selfHeightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (selfWidthMode == MeasureSpec.EXACTLY) {
            resultWidth = selfWidthSize;
        } else {
            resultWidth = currentMaxWidth + mPaddingRight;
        }

        if (selfHeightMode == MeasureSpec.EXACTLY) {
            resultHeight = selfHeightSize;
        } else {
            resultHeight = totalHeight;
        }

        setMeasuredDimension(resultWidth, resultHeight);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int top = 0;
        int left = 0;
        int lineHeight = 0;
        for (int i = 0; i < mLines.size(); i++) {
            //第 i 行

            //每行的left从0开始
            left = 0;
            //每行的top从总高度算起
            top += lineHeight;
            lineHeight = 0;
            List<View> views = mLines.get(i);
            for (View view : views) {
                //列，即子View
                if (view.getVisibility() == GONE) {
                    continue;
                }

                MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();

                view.layout(
                        left + layoutParams.leftMargin + mPaddingLeft,
                        top + layoutParams.topMargin + mPaddingTop,
                        left + view.getMeasuredWidth() + layoutParams.leftMargin + mPaddingLeft,
                        top + view.getMeasuredHeight() + layoutParams.topMargin + mPaddingTop);

                lineHeight = Math.max(lineHeight, view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin);

                left = left + view.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            }
        }

    }

    private AutoAdapter mAdapter;

    public void setAdapter(AutoAdapter adapter) {
        if (null == adapter) {
            throw new NullPointerException("TagFlowAdapter is null, please check setAdapter(TagFlowAdapter adapter)...");
        }
        mAdapter = adapter;
        adapter.setOnNotifyDataSetChangedListener(new AutoAdapter.OnNotifyDataSetChangedListener() {
            @Override
            public void OnNotifyDataSetChanged() {
                notifyDataSetChanged();
            }
        });
        adapter.notifyDataSetChanged();
    }

    private void notifyDataSetChanged() {
        removeAllViews();
        if (mAdapter == null || mAdapter.getCount() == 0) {
            return;
        }
        ViewGroup.MarginLayoutParams layoutParams = new MarginLayoutParams(generateDefaultLayoutParams());
        for (int i = 0; i < mAdapter.getCount(); i++) {
            View view = mAdapter.getView(i);
            if (view == null) {
                throw new NullPointerException("item layout is null, please check getView()...");
            }
            addView(view, i, layoutParams);
        }
    }
}
