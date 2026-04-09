package fr.dialogue.azplugin.bukkit.compat.agent;

import fr.dialogue.azplugin.bukkit.compat.material.EnumDefinition;
import fr.dialogue.azplugin.bukkit.compat.material.NMSMaterialDefinitions;
import fr.dialogue.azplugin.common.utils.agent.Agent;
import fr.dialogue.azplugin.common.utils.asm.AddEnumConstantTransformer;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NMSMaterialTransformers {

    public static <T extends EnumDefinition> void registerNMSMaterialTransformer(
        Agent agent,
        String className,
        Function<? super T, ? extends AddEnumConstantTransformer.InitializerGenerator> initializerFn,
        Collection<? extends T> materials,
        boolean fixToolMaterialName1_8
    ) {
        agent.addTransformer(
            className,
            AddEnumConstantTransformer::new,
            materials
                .stream()
                .map(material -> {
                    String name = material.getName();
                    if (fixToolMaterialName1_8) {
                        name = NMSMaterialDefinitions.fixToolMaterialName1_8(name);
                    }
                    return new AddEnumConstantTransformer.EnumConstant(name, initializerFn.apply(material));
                })
                .collect(Collectors.toList())
        );
    }
}
