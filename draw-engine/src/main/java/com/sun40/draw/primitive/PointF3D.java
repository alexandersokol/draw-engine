package com.sun40.draw.primitive;

/**
 * Created by Alexander Sokol
 * on 25.04.16.
 */
public class PointF3D {
    public float x;
    public float y;
    public float z;

    public PointF3D() {
    }

    public PointF3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public PointF3D(PointF3D p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }

    public PointF3D(double x, double y, double z) {
        this.x = (float) x;
        this.y = (float) y;
        this.z = (float) z;
    }

    /**
     * Set the point's x and y coordinates
     */
    public final void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Set the point's x and y coordinates to the coordinates of p
     */
    public final void set(PointF3D p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }

    public final void negate() {
        x = -x;
        y = -y;
        z = -z;
    }

    public final void offset(float dx, float dy, float dz) {
        x += dx;
        y += dy;
        z += dz;
    }


    public final boolean equals(float x, float y, float z) {
        return this.x == x && this.y == y && this.z == z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PointF3D pointF3DF = (PointF3D) o;

        return Float.compare(pointF3DF.x, x) == 0 &&
                Float.compare(pointF3DF.y, y) == 0 &&
                Float.compare(pointF3DF.z, z) == 0;

    }


    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        return result;
    }


    @Override
    public String toString() {
        return "PointF(" + x + ", " + y + ", " + z + ")";
    }

}