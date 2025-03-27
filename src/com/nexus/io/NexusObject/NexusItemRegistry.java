package com.nexus.io.NexusObject;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nexus.io.ResonanceCrystals.AdvanceLife;
import com.nexus.io.ResonanceCrystals.EchoicDisruption;
import com.nexus.io.ResonanceCrystals.EchoicSonar;
import com.nexus.io.ResonanceCrystals.MemoryAnchor;
import com.nexus.io.ResonanceCrystals.NullifyGravity;
import com.nexus.io.ResonanceCrystals.PocketDimension;
import com.nexus.io.ResonanceCrystals.Transmutation;
import com.nexus.io.ResonanceCrystals.VectorDisplacement;

/**
 * @Documented NexusItemRegistry A class to handle Nexus Objects for generation, debugging, etc.
 * @Description 
 *  		Get each class's internal constructor and allow for reflection to access internal methods. 
 *  		Finally, put each object into itemRegistry via their instance and refer to the object's internal name.
 *  		If the object could not be accessed, or doesn't exist, print a stacktrace.
 */
public class NexusItemRegistry 
{
	public static final Map<String, AbstractNexusObject> itemRegistry = new HashMap<>();

    public static void itemInit() 
    {
        List<Class<? extends AbstractNexusObject>> itemClasses = Arrays.asList(
            //Resonance Crystals
        	MemoryAnchor.class, 
            EchoicSonar.class, 
            NullifyGravity.class, 
            VectorDisplacement.class, 
            PocketDimension.class, 
            Transmutation.class, 
            EchoicDisruption.class, 
            AdvanceLife.class);
        
        for (Class<? extends AbstractNexusObject> clazz : itemClasses) 
        {
            try 
            {
                Constructor<? extends AbstractNexusObject> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                AbstractNexusObject instance = constructor.newInstance();
                itemRegistry.put(instance.getInternalName(), instance);
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
    }
}
