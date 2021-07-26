/*
 * This program is part of the OpenLMIS logistics management information system platform software.
 * Copyright © 2017 VillageReach
 *
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *  
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU Affero General Public License for more details. You should have received a copy of
 * the GNU Affero General Public License along with this program. If not, see
 * http://www.gnu.org/licenses.  For additional information contact info@OpenLMIS.org. 
 */

package org.openlmis.referencedata.service;

import org.openlmis.referencedata.domain.Orderable;
import org.openlmis.referencedata.dto.OrderableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderableIntegrationDataService {

  @Value("${service.url}")
  private String referencedataUrl;

  private static final String SERVICE_URL = "/api/integration/orderable";

  private final AuthService authService;

  @Autowired
  public OrderableIntegrationDataService(AuthService authService) {
    this.authService = authService;
  }

  /**
   * Send an orderable to one-network-integration-service.
   *
   * @param orderable object
   */
  public void sendOrderable(Orderable orderable) throws OrderableIntegrationDataException {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(authService.obtainAccessToken());
    OrderableDto orderableDto = OrderableDto.newInstance(orderable);
    String url = referencedataUrl + SERVICE_URL;

    try {
      restTemplate.exchange(
              RequestHelper.createUri(url),
              HttpMethod.POST,
              new HttpEntity<>(orderableDto, headers),
              Object.class);

    } catch (RestClientException ex) {
      throw new OrderableIntegrationDataException(
              "Unable to send orderable to one-network-integration-service.", ex
      );
    }
  }
}
