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
package org.icgc.dcc.storage.client.fs;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class StorageFileAttributes implements PosixFileAttributes {

  private StoragePath path;

  public StorageFileAttributes(Path path) {
    this.path = (StoragePath) path;
  }

  @Override
  public FileTime lastModifiedTime() {
    return FileTime.fromMillis(0);
  }

  @Override
  public FileTime lastAccessTime() {
    return FileTime.fromMillis(0);
  }

  @Override
  public FileTime creationTime() {
    return FileTime.fromMillis(0);
  }

  @Override
  public boolean isRegularFile() {
    return !path.toString().equals("/");
  }

  @Override
  public boolean isDirectory() {
    return path.toString().equals("/");
  }

  @Override
  public boolean isSymbolicLink() {
    return false;
  }

  @Override
  public boolean isOther() {
    return false;
  }

  @Override
  public long size() {
    return 666;
  }

  @Override
  public Object fileKey() {
    return path.toAbsolutePath().toString();
  }

  @Override
  public UserPrincipal owner() {
    return new UserPrincipal() {

      @Override
      public String getName() {
        return "icgc-user";
      }

    };
  }

  @Override
  public GroupPrincipal group() {
    return new GroupPrincipal() {

      @Override
      public String getName() {
        return "icgc-group";
      }

    };
  }

  @Override
  public Set<PosixFilePermission> permissions() {
    return ImmutableSet.of(PosixFilePermission.OWNER_READ);
  }

}