package com.company;

public class Resizer {
    static public Object[] addValue(Object[] objects, Object object){
        Object[] newObjects = new Object[objects.length+1];
        int i;
        for(i = 0; i < objects.length;i++){
            newObjects[i] = objects[i];
        }
        newObjects[i] = object;
        return newObjects;
    }

    static public Object[] removeValue(Object[] objects, int index){
        Object[] newObjects  = new Object[objects.length-1];
        for(int i = 0; i < index; i++){
            newObjects[i] = objects[i];
        }
        for(int i = index;i<newObjects.length;i++){
            newObjects[i] = objects[++i];
        }
        return newObjects;
    }

    static public Object[] clone(ICloneable[] objects){
        Object[] newObjects = new Object[objects.length];
        for(int i = 0; i < objects.length;i++){
            newObjects[i] = objects[i].clone();
        }
        return newObjects;
    }

    public static Object[] combine(Object[] a, Object[] b){
        int length = a.length + b.length;
        Object[] result = new Object[length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

}
