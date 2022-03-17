package org.egen.ecommerce.web.exception.custom;

public class PreconditionFailedException extends RuntimeException {

  private static final long serialVersionUID = -8564459572689056477L;

  public PreconditionFailedException(String msg) {
    super(msg);
  }
}
