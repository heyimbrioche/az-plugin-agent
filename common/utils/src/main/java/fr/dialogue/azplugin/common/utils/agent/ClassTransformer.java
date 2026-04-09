package fr.dialogue.azplugin.common.utils.agent;

@FunctionalInterface
public interface ClassTransformer {
    void transform(LoadingClass clazz);
}
