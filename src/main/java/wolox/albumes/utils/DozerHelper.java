package wolox.albumes.utils;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerHelper {

    public static <T, U> List<U> mapList(final Mapper mapper, final List<T> source, final Class<U> destType){

        final List<U> dest = new ArrayList<U>();

        if (source == null) return dest;

        for (T element : source) {
            if (element == null) {
                continue;
            }
            dest.add(mapper.map(element, destType));
        }

        List<U> s1 = new ArrayList<U>();
        s1.add(null);
        dest.removeAll(s1);

        return dest;
    }


    public static <T, U> List<U> mapList(final List<T> source, final Class<U> destType){
        final List<U> dest = new ArrayList<U>();

        if (source == null) return dest;

        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        for (T element : source) {
            if (element == null) {
                continue;
            }
            dest.add(mapper.map(element, destType));
        }

        List<U> s1 = new ArrayList<U>();
        s1.add(null);
        dest.removeAll(s1);

        return dest;
    }




    public static <T, U> U map(final T source, final Class<U> destType){
        if (source == null) return null;

        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        U dest = mapper.map(source, destType);
        return dest;
    }


    public static <T, U> U map(T source, U dest) {
        if (source == null) return null;

        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        mapper.map(source, dest);
        return dest;
    }



}