package com.daasuu.exoplayerfilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.daasuu.epf.filter.GlBilateralFilter;
import com.daasuu.epf.filter.GlBoxBlurFilter;
import com.daasuu.epf.filter.GlBulgeDistortionFilter;
import com.daasuu.epf.filter.GlCGAColorspaceFilter;
import com.daasuu.epf.filter.GlFilter;
import com.daasuu.epf.filter.GlFilterGroup;
import com.daasuu.epf.filter.GlGaussianBlurFilter;
import com.daasuu.epf.filter.GlGrayScaleFilter;
import com.daasuu.epf.filter.GlHazeFilter;
import com.daasuu.epf.filter.GlInvertFilter;
import com.daasuu.epf.filter.GlLookUpTableFilter;
import com.daasuu.epf.filter.GlMonochromeFilter;
import com.daasuu.epf.filter.GlSepiaFilter;
import com.daasuu.epf.filter.GlSharpenFilter;
import com.daasuu.epf.filter.GlSphereRefractionFilter;
import com.daasuu.epf.filter.GlToneCurveFilter;
import com.daasuu.epf.filter.GlVignetteFilter;
import com.daasuu.exoplayerfilter.filtersample.GlBitmapOverlaySample;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sudamasayuki on 2017/05/18.
 */

public enum FilterType {
    DEFAULT("原片"),
    BILATERAL_BLUR("双向模糊"),
    BOX_BLUR("方形模糊"),
    TONE_CURVE_SAMPLE("曲线"),
    LOOK_UP_TABLE_SAMPLE("查找"),
    BULGE_DISTORTION("膨胀变形"),
    CGA_COLORSPACE("注册色彩"),
    GAUSSIAN_FILTER("高斯模糊"),
    GRAY_SCALE("灰度"),
    HAZE("薄雾"),
    INVERT("倒置"),
    MONOCHROME("单色画"),
    SEPIA("深褐色"),
    SHARP("尖锐"),
    VIGNETTE("晕映"),
    FILTER_GROUP_SAMPLE("滤波器样本"),
    SPHERE_REFRACTION("球面折射"),
    BITMAP_OVERLAY_SAMPLE("贴图");
    private String value;

    FilterType(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public static List<FilterType> createFilterList() {
        List<FilterType> filters = new ArrayList<>();

        filters.add(DEFAULT);
        filters.add(SEPIA);
        filters.add(MONOCHROME);
        filters.add(TONE_CURVE_SAMPLE);
        filters.add(LOOK_UP_TABLE_SAMPLE);
        filters.add(VIGNETTE);
        filters.add(INVERT);
        filters.add(HAZE);
        filters.add(BOX_BLUR);
        filters.add(BILATERAL_BLUR);
        filters.add(GRAY_SCALE);
        filters.add(SPHERE_REFRACTION);
        filters.add(FILTER_GROUP_SAMPLE);
        filters.add(GAUSSIAN_FILTER);
        filters.add(BULGE_DISTORTION);
        filters.add(CGA_COLORSPACE);
        filters.add(SHARP);
        filters.add(BITMAP_OVERLAY_SAMPLE);

        return filters;
    }

    public static GlFilter createGlFilter(FilterType filterType, Context context) {
        switch (filterType) {
            case DEFAULT:
                return new GlFilter();
            case SEPIA:
                return new GlSepiaFilter();
            case GRAY_SCALE:
                return new GlGrayScaleFilter();
            case INVERT:
                return new GlInvertFilter();
            case HAZE:
                return new GlHazeFilter();
            case MONOCHROME:
                return new GlMonochromeFilter();
            case BILATERAL_BLUR:
                return new GlBilateralFilter();
            case BOX_BLUR:
                return new GlBoxBlurFilter();
            case LOOK_UP_TABLE_SAMPLE:
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.lookup_sample);

                return new GlLookUpTableFilter(bitmap);
            case TONE_CURVE_SAMPLE:
                try {
                    InputStream is = context.getAssets().open("acv/tone_cuver_sample.acv");
                    return new GlToneCurveFilter(is);
                } catch (IOException e) {
                    Log.e("FilterType", "Error");
                }
                return new GlFilter();

            case SPHERE_REFRACTION:
                return new GlSphereRefractionFilter();
            case VIGNETTE:
                return new GlVignetteFilter();
            case FILTER_GROUP_SAMPLE:
                return new GlFilterGroup(new GlSepiaFilter(), new GlVignetteFilter());
            case GAUSSIAN_FILTER:
                return new GlGaussianBlurFilter();
            case BULGE_DISTORTION:
                return new GlBulgeDistortionFilter();
            case CGA_COLORSPACE:
                return new GlCGAColorspaceFilter();
            case SHARP:
                GlSharpenFilter glSharpenFilter = new GlSharpenFilter();
                glSharpenFilter.setSharpness(4f);
                return glSharpenFilter;
            case BITMAP_OVERLAY_SAMPLE:
                return new GlBitmapOverlaySample(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round));
            default:
                return new GlFilter();
        }
    }


}
