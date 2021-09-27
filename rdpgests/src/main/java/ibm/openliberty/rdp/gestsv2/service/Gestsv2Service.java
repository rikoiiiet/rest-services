/*******************************************************************************
* Copyright (c) 2018, 2020 IBM Corporation and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*     IBM Corporation - Initial implementation
*******************************************************************************/

package ibm.openliberty.rdp.gestsv2.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ibm.openliberty.rdp.gestsv2.models.Gestsv2Record;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Gestsv2Service {

 @PersistenceContext(name = "jpa-unit")
 private EntityManager em;

 public void createGestsv2Record(Gestsv2Record gestsv2Record) {
     em.persist(gestsv2Record);
 }

 public Gestsv2Record readGestsv2Record(String id) {
     return em.find(Gestsv2Record.class, id);
 }

 public void updateGestsv2Record(Gestsv2Record entity, Long id) {
	 if (em.find(Gestsv2Record.class, id) == null) {
         throw new RuntimeException(Gestsv2Record.class + " with id[" + id + "] not found!");
     } else {
         em.merge(entity);
         
     }
 }
 
 public void deleteGestsv2Record(Gestsv2Record gestsv2Record) {
     em.remove(gestsv2Record);
 }

 public List<Gestsv2Record> readAllGestsv2Records() {
     return em.createNamedQuery("Gestsv2Record.findAll", Gestsv2Record.class).getResultList();
 }

 public Gestsv2Record findGestsv2Record(String id) {
     return em.createNamedQuery("Gestsv2Record.findOneRecord", Gestsv2Record.class)
         .setParameter("id", id).getSingleResult();
 }
 
 public List<Gestsv2Record> findQuerry(String sql) {
     return em.createQuery(sql, Gestsv2Record.class).getResultList();
 }
 
 public List<Gestsv2Record> readLast5Gestsv2Records() {
     return em.createNamedQuery("Gestsv2Record.findLast5", Gestsv2Record.class).getResultList();
 }
}