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

package org.openlmis.referencedata.events;

import org.openlmis.referencedata.domain.Orderable;
import org.openlmis.referencedata.extension.point.OrderableCreatePostProcessor;
import org.openlmis.referencedata.service.OrderableIntegrationDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "OrderableCreateEvent")
public class OrderableCreateEvent implements OrderableCreatePostProcessor {
  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final OrderableIntegrationDataService orderableIntegrationDataService;

  @Autowired
  public OrderableCreateEvent(OrderableIntegrationDataService orderableIntegrationDataService) {
    this.orderableIntegrationDataService = orderableIntegrationDataService;
  }

  @Override
  public void process(Orderable orderable) {
    logger.info("OrderableCreateEvent - processing");
    orderableIntegrationDataService.sendOrderable(orderable);
  }
}
