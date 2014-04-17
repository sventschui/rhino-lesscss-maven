package ch.sventschui.lesscss.configuration.utilities;

/*
 * (C) Copyright 2014 Sven Tschui (http://eniu.ch/) and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the GNU Lesser
 * General Public License (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

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
