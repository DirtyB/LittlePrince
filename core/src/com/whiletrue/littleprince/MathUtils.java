package com.whiletrue.littleprince;

/**
 * Created by boris_0mrym3f on 23.04.2017.
 */
public class MathUtils {

    public static float degreesToRadians(float degrees){
        return (float)(degrees*Math.PI/180f);
    }

    public static float radiansToDegrees(float degrees){
        return (float)(degrees*180f/Math.PI);
    }

    public static float normaliseAngle(float angle){
        float twopi = (float)Math.PI * 2;
        float remainder = angle%(twopi);
        if(remainder<0){
            remainder = twopi+remainder;
        }
        return remainder;
    }


}
