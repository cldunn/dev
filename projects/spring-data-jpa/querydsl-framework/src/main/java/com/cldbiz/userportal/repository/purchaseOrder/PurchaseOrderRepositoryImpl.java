package com.cldbiz.userportal.repository.purchaseOrder;

import java.util.List;

import com.cldbiz.userportal.domain.PurchaseOrder;
import com.cldbiz.userportal.domain.QAccount;
import com.cldbiz.userportal.domain.QCustomer;
import com.cldbiz.userportal.domain.QInvoice;
import com.cldbiz.userportal.domain.QLineItem;
import com.cldbiz.userportal.domain.QProduct;
import com.cldbiz.userportal.domain.QPurchaseOrder;
import com.cldbiz.userportal.domain.QContact;
import com.cldbiz.userportal.dto.AccountDto;
import com.cldbiz.userportal.dto.CustomerDto;
import com.cldbiz.userportal.dto.InvoiceDto;
import com.cldbiz.userportal.dto.LineItemDto;
import com.cldbiz.userportal.dto.ProductDto;
import com.cldbiz.userportal.dto.PurchaseOrderDto;
import com.cldbiz.userportal.repository.AbstractDaoImpl;
import com.cldbiz.userportal.repository.DynBooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;

public class PurchaseOrderRepositoryImpl extends AbstractDaoImpl<PurchaseOrder, PurchaseOrderDto, Long> implements PurchaseOrderRepositoryExt {

	@Override
	public Boolean existsByDto(PurchaseOrderDto purchaseOrderDto, Predicate... predicates) {
		QPurchaseOrder purchaseOrder = QPurchaseOrder.purchaseOrder;
		
		DynBooleanBuilder<QPurchaseOrder, PurchaseOrderDto> builder = searchByCriteria(purchaseOrderDto, predicates);
		
		return jpaQueryFactory.selectFrom(purchaseOrder).where(builder.asPredicate()).fetchCount() > 0 ? Boolean.TRUE : Boolean.FALSE;
	}
	
	@Override
	public Long countByDto(PurchaseOrderDto purchaseOrderDto, Predicate... predicates) {
		QPurchaseOrder purchaseOrder = QPurchaseOrder.purchaseOrder;
		
		DynBooleanBuilder<QPurchaseOrder, PurchaseOrderDto> builder = searchByCriteria(purchaseOrderDto, predicates);
		
		return jpaQueryFactory.selectFrom(purchaseOrder).where(builder.asPredicate()).fetchCount();
	}

	@Override
	public List<PurchaseOrder> findByIds(List<Long> purchaseOrderIds) {
		QPurchaseOrder purchaseOrder = QPurchaseOrder.purchaseOrder;
		QCustomer customer = QCustomer.customer;
		QAccount account = QAccount.account;
		QContact contact = QContact.contact;

		// join the entity to all "OnetoOne/ManyToOne" relationships via and innerJoin/fetchJoin
		// forces all columns for all tables in one select which is more efficient
		// includes dependency's dependencies
		return jpaQueryFactory.selectFrom(purchaseOrder)
				.innerJoin(purchaseOrder.account, account).fetchJoin()
				.innerJoin(account.customer, customer).fetchJoin()
				.innerJoin(account.contact, contact).fetchJoin()
				.where(purchaseOrder.id.in(purchaseOrderIds))
				.fetch();
	}
	
	@Override
	public List<PurchaseOrder> findAll() {
		QPurchaseOrder purchaseOrder = QPurchaseOrder.purchaseOrder;
		QCustomer customer = QCustomer.customer;
		QAccount account = QAccount.account;
		QContact contact = QContact.contact;

		// join the entity to all "OnetoOne/ManyToOne" relationships via and innerJoin/fetchJoin
		// forces all columns for all tables in one select which is more efficient
		// includes dependency's dependencies
		return jpaQueryFactory.selectFrom(purchaseOrder)
				.innerJoin(purchaseOrder.account, account).fetchJoin()
				.innerJoin(account.customer, customer).fetchJoin()
				.innerJoin(account.contact, contact).fetchJoin()
				.fetch();
	}

	@Override
	public List<PurchaseOrder> findByDto(PurchaseOrderDto purchaseOrderDto, Predicate... predicates) {
		QPurchaseOrder purchaseOrder = QPurchaseOrder.purchaseOrder;
		QCustomer customer = QCustomer.customer;
		QAccount account = QAccount.account;
		QContact contact = QContact.contact;
		
		DynBooleanBuilder<QPurchaseOrder, PurchaseOrderDto> builder = findByCriteria(purchaseOrderDto, predicates);
		
		// join the entity to all "OnetoOne/ManyToOne" relationships via and innerJoin/fetchJoin
		// forces all columns for all tables in one select which is more efficient
		// includes dependency's dependencies
		return jpaQueryFactory.selectFrom(purchaseOrder)
				.innerJoin(purchaseOrder.account, account).fetchJoin()
				.innerJoin(account.customer, customer).fetchJoin()
				.innerJoin(account.contact, contact).fetchJoin()
				.where(builder.asPredicate())
				.fetch();
	}

	@Override
	public List<PurchaseOrder> findPageByDto(PurchaseOrderDto purchaseOrderDto, Predicate... predicates) {
		QPurchaseOrder purchaseOrder = QPurchaseOrder.purchaseOrder;
		QCustomer customer = QCustomer.customer;
		QAccount account = QAccount.account;
		QContact contact = QContact.contact;

		DynBooleanBuilder<QPurchaseOrder, PurchaseOrderDto> builder = findByCriteria(purchaseOrderDto, predicates);
		
		// join the entity to all "OnetoOne/ManyToOne" relationships via and innerJoin/fetchJoin
		// forces all columns for all tables in one select which is more efficient
		// includes dependency's dependencies
		return jpaQueryFactory.selectFrom(purchaseOrder)
				.innerJoin(purchaseOrder.account, account).fetchJoin()
				.innerJoin(account.customer, customer).fetchJoin()
				.innerJoin(account.contact, contact).fetchJoin()
				.where(builder.asPredicate())
				.orderBy(sortBy(purchaseOrderDto))
				.offset(purchaseOrderDto.getStart().intValue())
				.limit(purchaseOrderDto.getLimit().intValue())
				.fetch();
	}

	@Override
	public List<PurchaseOrder> searchByDto(PurchaseOrderDto purchaseOrderDto, Predicate... predicates) {
		QPurchaseOrder purchaseOrder = QPurchaseOrder.purchaseOrder;
		QCustomer customer = QCustomer.customer;
		QAccount account = QAccount.account;
		QContact contact = QContact.contact;

		DynBooleanBuilder<QPurchaseOrder, PurchaseOrderDto> builder = searchByCriteria(purchaseOrderDto, predicates);
		
		// join the entity to all "OnetoOne/ManyToOne" relationships via and innerJoin/fetchJoin
		// forces all columns for all tables in one select which is more efficient
		// includes dependency's dependencies
		return jpaQueryFactory.selectFrom(purchaseOrder)
				.innerJoin(purchaseOrder.account, account).fetchJoin()
				.innerJoin(account.customer, customer).fetchJoin()
				.innerJoin(account.contact, contact).fetchJoin()
				.where(builder.asPredicate())
				.fetch();
	}

	@Override
	public List<PurchaseOrder> searchPageByDto(PurchaseOrderDto purchaseOrderDto, Predicate... predicates) {
		QPurchaseOrder purchaseOrder = QPurchaseOrder.purchaseOrder;
		QCustomer customer = QCustomer.customer;
		QAccount account = QAccount.account;
		QContact contact = QContact.contact;

		DynBooleanBuilder<QPurchaseOrder, PurchaseOrderDto> builder = searchByCriteria(purchaseOrderDto, predicates);
		
		// join the entity to all "OnetoOne/ManyToOne" relationships via and innerJoin/fetchJoin
		// forces all columns for all tables in one select which is more efficient
		// includes dependency's dependencies
		return jpaQueryFactory.selectFrom(purchaseOrder)
				.innerJoin(purchaseOrder.account, account).fetchJoin()
				.innerJoin(account.customer, customer).fetchJoin()
				.innerJoin(account.contact, contact).fetchJoin()
				.where(builder.asPredicate())
				.orderBy(sortBy(purchaseOrderDto))
				.offset(purchaseOrderDto.getStart().intValue())
				.limit(purchaseOrderDto.getLimit().intValue())
				.fetch();
	}

	protected DynBooleanBuilder<QPurchaseOrder, PurchaseOrderDto> findByCriteria(PurchaseOrderDto purchaseOrderDto, Predicate... predicates) {
		QPurchaseOrder purchaseOrder = QPurchaseOrder.purchaseOrder;

		DynBooleanBuilder<QPurchaseOrder, PurchaseOrderDto> builder = new DynBooleanBuilder<QPurchaseOrder, PurchaseOrderDto>();
		builder = builder.findPredicate(purchaseOrder, purchaseOrderDto, predicates);

		if (purchaseOrderDto.getAccountDto() != null) {
			DynBooleanBuilder<QAccount, AccountDto> byAccountBuilder = new DynBooleanBuilder<QAccount, AccountDto>();
			Predicate byAccountPredicate = byAccountBuilder.findPredicate(purchaseOrder.account, purchaseOrderDto.getAccountDto(), predicates).asPredicate();
			builder.and(byAccountPredicate);
		}

		if (purchaseOrderDto.asParam.getLineItemDto() != null) {
			DynBooleanBuilder<QLineItem, LineItemDto> byLineItemBuilder = new DynBooleanBuilder<QLineItem, LineItemDto>();
			Predicate byLineItemPredicate = byLineItemBuilder.findPredicate(purchaseOrder.lineItems.any(), purchaseOrderDto.asParam.getLineItemDto(), predicates).asPredicate();
			builder.and(byLineItemPredicate);
			
			if (purchaseOrderDto.asParam.getLineItemDto().getProductDto() != null) {
				DynBooleanBuilder<QProduct, ProductDto> byProductBuilder = new DynBooleanBuilder<QProduct, ProductDto>();
				Predicate byProductPredicate = byProductBuilder.findPredicate(purchaseOrder.lineItems.any().product, purchaseOrderDto.asParam.getLineItemDto().getProductDto(), predicates).asPredicate();
				builder.and(byProductPredicate);
			}
		}

		return builder;
	}
	
	protected DynBooleanBuilder<QPurchaseOrder, PurchaseOrderDto> searchByCriteria(PurchaseOrderDto purchaseOrderDto, Predicate... predicates) {
		QPurchaseOrder purchaseOrder = QPurchaseOrder.purchaseOrder;

		DynBooleanBuilder<QPurchaseOrder, PurchaseOrderDto> builder = new DynBooleanBuilder<QPurchaseOrder, PurchaseOrderDto>();
		builder = builder.searchPredicate(purchaseOrder, purchaseOrderDto, predicates);

		if (purchaseOrderDto.getAccountDto() != null) {
			DynBooleanBuilder<QAccount, AccountDto> byAccountBuilder = new DynBooleanBuilder<QAccount, AccountDto>();
			Predicate byAccountPredicate = byAccountBuilder.searchPredicate(purchaseOrder.account, purchaseOrderDto.getAccountDto(), predicates).asPredicate();
			builder.and(byAccountPredicate);
		}

		if (purchaseOrderDto.asParam.getLineItemDto() != null) {
			DynBooleanBuilder<QLineItem, LineItemDto> byLineItemBuilder = new DynBooleanBuilder<QLineItem, LineItemDto>();
			Predicate byLineItemPredicate = byLineItemBuilder.searchPredicate(purchaseOrder.lineItems.any(), purchaseOrderDto.asParam.getLineItemDto(), predicates).asPredicate();
			builder.and(byLineItemPredicate);
			
			if (purchaseOrderDto.asParam.getLineItemDto().getProductDto() != null) {
				DynBooleanBuilder<QProduct, ProductDto> byProductBuilder = new DynBooleanBuilder<QProduct, ProductDto>();
				Predicate byProductPredicate = byProductBuilder.searchPredicate(purchaseOrder.lineItems.any().product, purchaseOrderDto.asParam.getLineItemDto().getProductDto(), predicates).asPredicate();
				builder.and(byProductPredicate);
			}
		}

		return builder;
	}

	@Override
	public OrderSpecifier[] sortBy(PurchaseOrderDto purchaseOrderDto) {
		PathBuilder pb = new PathBuilder<QPurchaseOrder>(QPurchaseOrder.class, "purchaseOrder");
		return sortOrderOf(pb, purchaseOrderDto);
	}

}
