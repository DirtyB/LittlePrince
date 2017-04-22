package com.whiletrue.littleprince;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import org.ejml.simple.SimpleMatrix;

/**
 * Created by boris_0mrym3f on 22.04.2017.
 */
public class PlanetOutline {

    private final static short SEGMENT_COUNT = 360;
    public final static short[] TRIANGLES = new short[SEGMENT_COUNT*3];

    static {
        for (short i=0; i<SEGMENT_COUNT; i++){
            TRIANGLES[i*3] = 0;
            TRIANGLES[i*3+1] = (short)(i+1);
            TRIANGLES[i*3+2] = (short)(i+2);
        }
        TRIANGLES[SEGMENT_COUNT*3-1] = (short)1;
    }

    private float[] radiusValues = new float[SEGMENT_COUNT];


    public PlanetOutline(float initialRadius){
        for (int i=0; i<SEGMENT_COUNT; i++){
            radiusValues[i] = initialRadius;
        }
    }

    public float radius(float angle){
        int segmentNumber = (int)(Math.round((angle/(2*Math.PI))*SEGMENT_COUNT)%SEGMENT_COUNT);
        return radiusValues[segmentNumber];
    }

    float angle(int index){
        return (float)((Math.PI*2)/SEGMENT_COUNT)*index;
    }

    float getX(float angle, float radius){
        return (float) (radius*Math.cos(angle));
    }
    float getY(float angle, float radius){
        return (float) (radius*Math.sin(angle));
    }

    public float[] getVertices(float originX, float originY, float scale){
        float[] vertices = new float[(SEGMENT_COUNT+1)*2];
        vertices[0] = originX;
        vertices[1] = originY;
        for(int i=0; i<SEGMENT_COUNT; i++){
            float angle = angle(i);
            vertices[(i+1)*2] = originX + scale * getX(angle,radiusValues[i]);
            vertices[(i+1)*2+1] = originY + scale * getY(angle,radiusValues[i]);
        }
        return vertices;
    }

    public void cutLine(float k, float b){
        SimpleMatrix A = new SimpleMatrix(2,2);
        A.set(0, 0, k);
        A.set(0, 1, -1);
        A.set(1, 1, -1);
        SimpleMatrix right = new SimpleMatrix(2,1);
        right.set(0, 0, -b);
        right.set(1, 0, 0);
        for (int i=0; i<SEGMENT_COUNT; i++){
            float angle = angle(i);
            A.set(1, 0, (Math.tan(angle)));
            SimpleMatrix rVect = A.solve(right);
            double x = rVect.get(0,0);
            double y = rVect.get(1,0);
            double scalarProduct = x * Math.cos(angle) + y * Math.sin(angle);
            double newRadius = Math.sqrt(x*x+y*y);
            if(scalarProduct>0 && newRadius < radiusValues[i]){
                radiusValues[i] = (float)newRadius;
            }
        }
    }

    /*public Polygon getPolygon(){
        return new Polygon(getVertices());
    }*/

}
