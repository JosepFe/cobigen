package com.devonfw.cobigen.javaplugin.inputreader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class CompositeClassLoader extends ClassLoader {

  private List<ClassLoader> classLoaders = new ArrayList<>(2);

  public CompositeClassLoader(ClassLoader main, ClassLoader parent) {

    this.classLoaders.add(main);
    this.classLoaders.add(parent);
  }

  @Override
  public Class<?> loadClass(String name) throws ClassNotFoundException {

    for (ClassLoader cl : this.classLoaders) {
      try {
        return cl.loadClass(name);
      } catch (ClassNotFoundException e) {
        continue;
      }
    }
    throw new ClassNotFoundException("No Class definition found in one of the classloaders "
        + this.classLoaders.stream().map(cl -> cl.toString()).collect(Collectors.toList()));
  }
}
