package org.springframework.hateoas.mediatype.hal;

public abstract class _FactoryProvider {
  public static HalMediaTypeConfigurationProvider halMediaTypeConfigurationProvider() {
    return new HalMediaTypeConfigurationProvider();
  }

  public static HalTraversonDefaults halTraversonDefaults() {
    return new HalTraversonDefaults();
  }
}
