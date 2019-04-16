package com.arborcommunity.net.glide;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by pc on 2018/9/9.
 */
public class CachingGlideModule implements GlideModule {
    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
       // glide.register(Model.class, Data.class, new ModelLoaderFactory());
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // 设置磁盘缓存为100M，缓存在内部缓存目录
        int cacheSize100MegaBytes = 104857600;
        builder.setDiskCache(
                new InternalCacheDiskCacheFactory(context, cacheSize100MegaBytes)
        );
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, cacheSize100MegaBytes));

        // 20%大的内存缓存作为 Glide 的默认值
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context).build();
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);

        builder.setMemoryCache( new LruResourceCache(customMemoryCacheSize));
        builder.setBitmapPool( new LruBitmapPool(customBitmapPoolSize));
    }


}
