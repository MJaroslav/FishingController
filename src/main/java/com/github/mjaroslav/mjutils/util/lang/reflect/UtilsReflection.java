package com.github.mjaroslav.mjutils.util.lang.reflect;

import cpw.mods.fml.relauncher.ReflectionHelper;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class UtilsReflection {
    private final Map<Class<?>, Type[]> GENERIC_CACHE = new HashMap<>();

    public @NotNull String getSimpleClassName(@NotNull Object cls) {
        String className;
        if (cls instanceof String)
            className = (String) cls;
        else if (cls instanceof Class<?>)
            className = ((Class<?>) cls).getSimpleName();
        else
            className = cls.getClass().getSimpleName();
        return className.contains(".") ? className.substring(className.lastIndexOf(".") + 1) : "";
    }

    public @NotNull String getPackageFromClass(@NotNull Object cls) {
        String className;
        if (cls instanceof String)
            className = (String) cls;
        else if (cls instanceof Class<?>)
            className = ((Class<?>) cls).getName();
        else
            className = cls.getClass().getName();
        return className.contains(".") ? className.substring(0, className.lastIndexOf(".")) : "";
    }

    public @NotNull Field findFieldUpTo(@NotNull Class<?> clazz, @Nullable Class<?> exclusiveParent,
                                        @NotNull String @NotNull ... fieldNames) {
        Exception failed = null;
        val fields = getFieldsUpTo(clazz, exclusiveParent);
        for (var fieldName : fieldNames)
            try {
                if (fields.containsKey(fieldName)) {
                    val f = fields.get(fieldName); // Search in parents too
                    f.setAccessible(true);
                    return f;
                }
            } catch (Exception e) {
                failed = e;
            }
        throw new ReflectionHelper.UnableToFindFieldException(fieldNames, failed);
    }

    public @NotNull Map<String, Field> getFieldsUpTo(@NotNull Class<?> startClass, @Nullable Class<?> exclusiveParent) {
        val result = new HashMap<String, Field>();
        var currentClass = startClass;
        do {
            for (var field : currentClass.getDeclaredFields())
                result.put(field.getName(), field);
            currentClass = startClass.getSuperclass();
        } while ((currentClass != null && (!(currentClass.equals(exclusiveParent)))));
        return result;
    }


    @SuppressWarnings("unchecked")
    public @NotNull <T, E> T getPrivateValueUpTo(@NotNull Class<? super E> classToAccess, @Nullable E instance,
                                                 @Nullable Class<?> exclusiveParent,
                                                 @NotNull String @NotNull ... fieldNames) {
        try {
            return (T) findFieldUpTo(classToAccess, exclusiveParent, fieldNames).get(instance);
        } catch (Exception e) {
            throw new ReflectionHelper.UnableToAccessFieldException(fieldNames, e);
        }
    }

    public <T, E> void setPrivateValueUpTo(@NotNull Class<? super T> classToAccess, @Nullable T instance,
                                           @Nullable E value, @Nullable Class<?> exclusiveParent,
                                           @NotNull String @NotNull ... fieldNames) {
        try {
            findFieldUpTo(classToAccess, exclusiveParent, fieldNames).set(instance, value);
        } catch (Exception e) {
            throw new ReflectionHelper.UnableToAccessFieldException(fieldNames, e);
        }
    }

    public @NotNull Class<?> getParameterizedClass(@NotNull Class<?> clazz, int index) {
        var result = GENERIC_CACHE.get(clazz);
        if (result != null)
            return (Class<?>) result[index];
        var generics = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments();
        GENERIC_CACHE.put(clazz, generics);
        return (Class<?>) generics[index];
    }
}
