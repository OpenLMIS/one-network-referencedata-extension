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

package org.openlmis.referencedata.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.openlmis.referencedata.testbuilder.OrderableDataBuilder;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class OrderableCreateEventTest {

  private Orderable generateInstance() {
    return new OrderableDataBuilder().build();
  }

  @Test
  public void shouldCreateNotificationAfterNewOrderableCreated() {

    Orderable orderable = generateInstance();

    OrderableCreateEvent observable = new OrderableCreateEvent();
    ProductsObserver observer = new ProductsObserver();

    observable.addPropertyChangeListener(observer);

    observable.process(orderable);
    assertEquals(observer.getProduct(), orderable);
  }
}
