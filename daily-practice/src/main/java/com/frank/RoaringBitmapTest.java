package com.frank;

import org.roaringbitmap.longlong.Roaring64Bitmap;

/**
 * @author: maxinhang.
 */
public class RoaringBitmapTest {
    public static void main(String[] args) {
        Roaring64Bitmap bitmap = new Roaring64Bitmap();
        bitmap.add(1L);
        bitmap.add(100000000L);
        bitmap.add(2000000000000L);
        bitmap.add(3000000000000L);
    }
}
