/*
Copyright (c) 2010 James Ahlborn

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307
USA
*/

package com.healthmarketscience.jackcess.impl;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Interface for a handler which can encode/decode a specific access page
 * encoding.
 *
 * @author James Ahlborn
 */
public interface CodecHandler 
{
  /**
   * Returns {@code true} if this handler can encode partial pages,
   * {@code false} otherwise.  If this method returns {@code false}, the
   * {@link #encodePage} method will never be called with a non-zero
   * pageOffset.
   */
  public boolean canEncodePartialPage();

  /**
   * Returns {@code true} if this handler can decode a page inline,
   * {@code false} otherwise.  If this method returns {@code false}, the
   * {@link #decodePage} method will always be called with separate buffers.
   */
  public boolean canDecodeInline();

  /**
   * Decodes the given page buffer.
   *
   * @param inPage the page to be decoded
   * @param outPage the decoded page.  if {@link #canDecodeInline} is {@code
   *                true}, this will be the same buffer as inPage.
   * @param pageNumber the page number of the given page
   * 
   * @throws IOException if an exception occurs during decoding
   */
  public void decodePage(ByteBuffer inPage, ByteBuffer outPage, int pageNumber) 
    throws IOException;

  /**
   * Encodes the given page buffer into a new page buffer and returns it.  The
   * returned page buffer will be used immediately and discarded so that it
   * may be re-used for subsequent page encodings.
   *
   * @param page the page to be encoded, should not be modified
   * @param pageNumber the page number of the given page
   * @param pageOffset offset within the page at which to start writing the
   *                   page data
   * 
   * @throws IOException  if an exception occurs during decoding
   *
   * @return the properly encoded page buffer for the given page buffer 
   */
  public ByteBuffer encodePage(ByteBuffer page, int pageNumber, 
                               int pageOffset) 
    throws IOException;
}
