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
package collaboratory.storage.object.store.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;

/**
 * check health for object upload service
 */
@Component
public class ObjectUploadServiceHealth implements HealthIndicator {

  @Autowired
  AmazonS3 s3;

  @Value("${collaboratory.bucket.name}")
  String bucketName;

  @Override
  public Health health() {
    Health.Builder builder = new Health.Builder();
    /*
     * String qstring = ((ServletRequestAttributes)
     * RequestContextHolder.getRequestAttributes()).getRequest().getQueryString(); List<NameValuePair> parameters =
     * URLEncodedUtils.parse(qstring, Charsets.US_ASCII); ArrayList<NameValuePair> tokens =
     * Lists.newArrayList(Collections2.filter(parameters, new Predicate<NameValuePair>() {
     * 
     * @Override public boolean apply(@Nullable NameValuePair input) { if (input != null &&
     * input.getName().equals("dcc-token")) { return true; } return false; } }));
     * 
     * if (tokens.size() != 1) { builder.outOfService(); } else {
     */
    // check if the aws account can access the bucket
    boolean foundBucket = true;
    try {
      foundBucket = s3.doesBucketExist(bucketName);
    } catch (Exception e) {
      foundBucket = false;
    }

    if (foundBucket) {
      builder.up();
    } else {
      builder.outOfService();
    }

    builder.withDetail("foundBucket", foundBucket);
    // }
    return builder.build();
  }
}
