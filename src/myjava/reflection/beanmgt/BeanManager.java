package myjava.reflection.beanmgt;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import myjava.reflection.annotation.Inject;
import myjava.reflection.annotation.Provide;
import myjava.reflection.orm.EntityManger;
import myjava.reflection.orm.ManagedEntityManger;
import myjava.reflection.provider.H2ConnectionProvider;

public class BeanManager {

    private static BeanManager instance = new BeanManager();

    public static BeanManager getInstance() {
        return instance;
    }

    private Map<Class<?>, Supplier<?>> registry;

    private BeanManager() {
        registry = new HashMap<>();
        List<Class<?>> classes = List.of(H2ConnectionProvider.class);
        for (Class<?> clss : classes) {
            Method[] methods = clss.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getAnnotation(Provide.class) != null) {
                    Supplier<?> supplier = () -> {
                        int mod = method.getModifiers();
                        try {
                            return Modifier.isStatic(mod) ? method.invoke(null)
                                    : method.invoke(clss.getConstructor().newInstance());
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                                | InstantiationException | NoSuchMethodException | SecurityException e) {
                            throw new RuntimeException(e);
                        }
                    };
                    registry.put(method.getReturnType(), supplier);
                }
            }
        }
    }

    public <T> EntityManger<T> getEntityManager(Class<T> entityClass) {
        EntityManger<T> em = new ManagedEntityManger<>(entityClass);
        injectDependencies(em);
        return em;
    }

    public <T> T getBean(Class<T> clss, Object... args) {
        T bean;
        try {
            bean = (args == null || args.length == 0) ? clss.getConstructor().newInstance()
                    : clss.getConstructor(Arrays.stream(args).map(arg -> arg.getClass()).toArray(Class[]::new))
                            .newInstance(args);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
        injectDependencies(bean);
        return bean;
    }

    private <T> void injectDependencies(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(Inject.class) != null) {
                Supplier<?> supplier = registry.get(field.getType());
                if (supplier != null) {
                    field.setAccessible(true);
                    try {
                        field.set(obj, supplier.get());
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

}
