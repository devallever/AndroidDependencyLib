//package com.allever.lib.ui.widget;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.View;
//import android.view.ViewGroup;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CustomViewGroup extends ViewGroup {
//
//    private List<Integer> mChildLeft = new ArrayList<>();
//    private List<Integer> mChildTop = new ArrayList<>();
//    private List<Integer> mChildRight = new ArrayList<>();
//    private List<Integer> mChildBottom = new ArrayList<>();
//
//
//    public CustomViewGroup(Context context) {
//        this(context, null);
//
//    }
//
//    public CustomViewGroup(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init();
//    }
//
//    private void init() {
//
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//        for (int i = 0; i < getChildCount(); i++) {
//            View childView = getChildAt(i);
//
//            LayoutParams layoutParams = childView.getLayoutParams();
//
//            //1.
//            //获取xml文件的layout_width 和layout_height
//            //如果是match_parent -> MATCH_PARENT 转换成常量
//            //wrap_content      -> WRAP_CONTENT
//            //dp/sp             -> 具体像素值
//            //这是开发者对子view的尺寸要求
//            int width = layoutParams.width;
//            int height = layoutParams.height;
//
//            //结合自己(ViewGroup)的可用空间和对子View的要求(width height)
//            int childWidthSpec;
//            int selfWidthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
//            int selfWidthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
//            switch (width) {
//                case LayoutParams.MATCH_PARENT:
//                    //childWidthSpec = MeasureSpec.makeMeasureSpec(可用空间, MeasureSpec.EXACTLY);
//                    if (selfWidthSpecMode == MeasureSpec.EXACTLY || selfWidthSpecMode == MeasureSpec.AT_MOST) {
//                        //可用空间 减去 已用空间
//                        childWidthSpec = MeasureSpec.makeMeasureSpec(selfWidthSpecSize - usedWidth, MeasureSpec.EXACTLY);
//                    } else {
//                        childWidthSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
//                    }
//                    break;
//                case LayoutParams.WRAP_CONTENT:
//                    if (selfWidthSpecMode == MeasureSpec.EXACTLY || selfWidthSpecMode == MeasureSpec.AT_MOST) {
//                        //可用空间 减去 已用空间
//                        childWidthSpec = MeasureSpec.makeMeasureSpec(selfWidthSpecSize - usedWidth, MeasureSpec.AT_MOST);
//                    } else {
//                        childWidthSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
//                    }
//                    break;
//                default:
//                    //xxxdp -> 精确值模式
//                    childWidthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
//                    break;
//            }
//
//            int childHeightSpec;
//            int selfHeightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
//            int selfHeightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
//            switch (height) {
//                case LayoutParams.MATCH_PARENT:
//                    //childHeightSpec = MeasureSpec.makeMeasureSpec(自己的可用宽高度, MeasureSpec.EXACTLY);
//                    if (selfHeightSpecMode == MeasureSpec.EXACTLY || selfHeightSpecMode == MeasureSpec.AT_MOST) {
//                        //可用空间 减去 已用空间
//                        childHeightSpec = MeasureSpec.makeMeasureSpec(selfHeightSpecSize - usedHeight, MeasureSpec.EXACTLY);
//                    } else {
//                        childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
//                    }
//
//                    break;
//                case LayoutParams.WRAP_CONTENT:
//                    if (selfHeightSpecMode == MeasureSpec.EXACTLY || selfHeightSpecMode == MeasureSpec.AT_MOST) {
//                        childHeightSpec = MeasureSpec.makeMeasureSpec(selfHeightSpecSize - usedHeight, MeasureSpec.AT_MOST);
//                    } else {
//                        childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
//                    }
//                    break;
//                default:
//                    childHeightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
//                    break;
//            }
//
//            //2.
//            //保存每个子View的位置和尺寸
//
//
//
//            //3.保存自身的尺寸 setMeasureDimension
//
//
//        }
//
//
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        for (int i = 0; i < getChildCount(); i++) {
//            View childView = getChildAt(i);
//            childView.layout(mChildLeft.get(i), mChildTop.get(i), mChildRight.get(i), mChildBottom.get(i));
//        }
//    }
//
//}
