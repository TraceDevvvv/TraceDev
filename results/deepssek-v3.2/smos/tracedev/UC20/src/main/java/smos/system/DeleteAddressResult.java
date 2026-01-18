package smos.system;

/**
 * Enumeration of possible results from address deletion.
 */
public enum DeleteAddressResult {
    ADDRESS_DELETED_SUCCESSFULLY,
    ADDRESS_HAS_ASSOCIATED_CLASSES,
    ADDRESS_NOT_FOUND
}