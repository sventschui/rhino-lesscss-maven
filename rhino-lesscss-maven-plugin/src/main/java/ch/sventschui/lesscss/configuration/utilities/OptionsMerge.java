package ch.sventschui.lesscss.configuration.utilities;

import java.lang.reflect.Field;

import ch.sventschui.lesscss.configuration.Options;

public class OptionsMerge {
    public static Options merge(Options o1, Options o2) throws OptionsMergeFailedException {
        
        if(o1 == null) {
            if(o2 == null) {
                return new Options();
            } else {
                return merge(o2, null);
            }
        } 
        
        try {
            Options merged = new Options();

            Field[] fields = Options.class.getDeclaredFields();

            for (Field field : fields) {
                // TODO: use getters/setters instead
                field.setAccessible(true);
                
                field.set(merged, field.get(o1));

                if(o2 != null) {
                    Object val = field.get(o2);

                    if (val != null) {

                        field.set(merged, field.get(o2));
                    }
                }
                
            }

            return merged;

        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new OptionsMergeFailedException(e);
        }
    }
}
