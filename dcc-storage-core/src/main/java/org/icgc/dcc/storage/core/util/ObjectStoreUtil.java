/*
 * Copyright (c) 2015 The Ontario Institute for Cancer Research. All rights reserved.                             
 *                                                                                                               
 * This program and the accompanying materials are made available under the terms of the GNU Public License v3.0.
 * You should have received a copy of the GNU General Public License along with                                  
 * this program. If not, see <http://www.gnu.org/licenses/>.                                                     
 *                                                                                                               
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY                           
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES                          
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT                           
 * SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,                                
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED                          
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;                               
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER                              
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN                         
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.icgc.dcc.storage.core.util;

import org.icgc.dcc.storage.core.model.Part;

/**
 * Helpers functions for the object store
 */
public class ObjectStoreUtil {

  /**
   * Returns S3 key for actual object blob
   * @param dataDir
   * @param objectId
   * @return
   */
  public static String getObjectKey(String dataDir, String objectId) {
    return dataDir + "/" + objectId;
  }

  /**
   * Returns S3 key for metadata file for blob (contains upload id's, MD5 checksums, pre-signed URL's for each part of
   * file)
   * @param dataDir
   * @param objectId
   * @return
   */
  public static String getObjectMetaKey(String dataDir, String objectId) {
    return dataDir + "/" + objectId + ".meta";
  }

  /**
   * Generates Range header for URL
   * @param part
   * @return
   */
  public static String getHttpRangeValue(Part part) {
    return String.valueOf("bytes=" + part.getOffset()) + "-"
        + String.valueOf(part.getOffset() + part.getPartSize() - 1L);
  }
}