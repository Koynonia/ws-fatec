/**
 * @author Fernando Moraes Oliveira
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 29/04/2016
 */

package controller;


import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import entity.Livro;;

public class OrdenaLivro implements Comparator<Livro> {

	@Override
	public int compare(Livro primeiro, Livro segundo) {

		return primeiro.getDtCadastro().compareTo(segundo.getDtCadastro());
	}
	
	public class OrdenaPublicacao implements Comparator<Livro> {

		@Override
		public int compare(Livro primeiro, Livro segundo) {

			return primeiro.getDtPublicacao().compareTo(segundo.getDtPublicacao());
		}

		@Override
		public Comparator<Livro> reversed() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparing(Comparator<? super Livro> other) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <U> Comparator<Livro> thenComparing(
				Function<? super Livro, ? extends U> keyExtractor,
				Comparator<? super U> keyComparator) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <U extends Comparable<? super U>> Comparator<Livro> thenComparing(
				Function<? super Livro, ? extends U> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingInt(
				ToIntFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingLong(
				ToLongFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingDouble(
				ToDoubleFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> nullsFirst(
				Comparator<? super T> comparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> nullsLast(
				Comparator<? super T> comparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T, U> Comparator<T> comparing(
				Function<? super T, ? extends U> keyExtractor,
				Comparator<? super U> keyComparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T, U extends Comparable<? super U>> Comparator<T> comparing(
				Function<? super T, ? extends U> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingInt(
				ToIntFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingLong(
				ToLongFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingDouble(
				ToDoubleFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	public class OrdenaCadastro implements Comparator<Livro> {

		@Override
		public int compare(Livro primeiro, Livro segundo) {

			return primeiro.getIsbn().compareTo(segundo.getIsbn());
		}

		@Override
		public Comparator<Livro> reversed() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparing(Comparator<? super Livro> other) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <U> Comparator<Livro> thenComparing(
				Function<? super Livro, ? extends U> keyExtractor,
				Comparator<? super U> keyComparator) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <U extends Comparable<? super U>> Comparator<Livro> thenComparing(
				Function<? super Livro, ? extends U> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingInt(
				ToIntFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingLong(
				ToLongFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingDouble(
				ToDoubleFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> nullsFirst(
				Comparator<? super T> comparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> nullsLast(
				Comparator<? super T> comparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T, U> Comparator<T> comparing(
				Function<? super T, ? extends U> keyExtractor,
				Comparator<? super U> keyComparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T, U extends Comparable<? super U>> Comparator<T> comparing(
				Function<? super T, ? extends U> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingInt(
				ToIntFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingLong(
				ToLongFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingDouble(
				ToDoubleFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}	
	}

	public class OrdenaTitulo implements Comparator<Livro> {

		@Override
		public int compare(Livro primeiro, Livro segundo) {

			return primeiro.getTitulo().compareTo(segundo.getTitulo());
		}

		@Override
		public Comparator<Livro> reversed() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparing(Comparator<? super Livro> other) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <U> Comparator<Livro> thenComparing(
				Function<? super Livro, ? extends U> keyExtractor,
				Comparator<? super U> keyComparator) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <U extends Comparable<? super U>> Comparator<Livro> thenComparing(
				Function<? super Livro, ? extends U> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingInt(
				ToIntFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingLong(
				ToLongFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingDouble(
				ToDoubleFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> nullsFirst(
				Comparator<? super T> comparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> nullsLast(
				Comparator<? super T> comparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T, U> Comparator<T> comparing(
				Function<? super T, ? extends U> keyExtractor,
				Comparator<? super U> keyComparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T, U extends Comparable<? super U>> Comparator<T> comparing(
				Function<? super T, ? extends U> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingInt(
				ToIntFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingLong(
				ToLongFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingDouble(
				ToDoubleFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	public class OrdenaAutor implements Comparator<Livro> {

		@Override
		public int compare(Livro primeiro, Livro segundo) {

			return primeiro.getAutor().compareTo(segundo.getAutor());
		}

		@Override
		public Comparator<Livro> reversed() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparing(Comparator<? super Livro> other) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <U> Comparator<Livro> thenComparing(
				Function<? super Livro, ? extends U> keyExtractor,
				Comparator<? super U> keyComparator) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <U extends Comparable<? super U>> Comparator<Livro> thenComparing(
				Function<? super Livro, ? extends U> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingInt(
				ToIntFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingLong(
				ToLongFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingDouble(
				ToDoubleFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> nullsFirst(
				Comparator<? super T> comparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> nullsLast(
				Comparator<? super T> comparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T, U> Comparator<T> comparing(
				Function<? super T, ? extends U> keyExtractor,
				Comparator<? super U> keyComparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T, U extends Comparable<? super U>> Comparator<T> comparing(
				Function<? super T, ? extends U> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingInt(
				ToIntFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingLong(
				ToLongFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingDouble(
				ToDoubleFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	public class OrdenaEditora implements Comparator<Livro> {

		@Override
		public int compare(Livro primeiro, Livro segundo) {

			return primeiro.getEditora().compareTo(segundo.getEditora());
		}

		@Override
		public Comparator<Livro> reversed() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparing(Comparator<? super Livro> other) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <U> Comparator<Livro> thenComparing(
				Function<? super Livro, ? extends U> keyExtractor,
				Comparator<? super U> keyComparator) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <U extends Comparable<? super U>> Comparator<Livro> thenComparing(
				Function<? super Livro, ? extends U> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingInt(
				ToIntFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingLong(
				ToLongFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingDouble(
				ToDoubleFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> nullsFirst(
				Comparator<? super T> comparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> nullsLast(
				Comparator<? super T> comparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T, U> Comparator<T> comparing(
				Function<? super T, ? extends U> keyExtractor,
				Comparator<? super U> keyComparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T, U extends Comparable<? super U>> Comparator<T> comparing(
				Function<? super T, ? extends U> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingInt(
				ToIntFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingLong(
				ToLongFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingDouble(
				ToDoubleFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	
	public class OrdenaCapa implements Comparator<Livro> {

		@Override
		public int compare(Livro primeiro, Livro segundo) {

			return primeiro.getCapa().compareTo(segundo.getCapa());
		}

		@Override
		public Comparator<Livro> reversed() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparing(Comparator<? super Livro> other) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <U> Comparator<Livro> thenComparing(
				Function<? super Livro, ? extends U> keyExtractor,
				Comparator<? super U> keyComparator) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <U extends Comparable<? super U>> Comparator<Livro> thenComparing(
				Function<? super Livro, ? extends U> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingInt(
				ToIntFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingLong(
				ToLongFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingDouble(
				ToDoubleFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> nullsFirst(
				Comparator<? super T> comparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> nullsLast(
				Comparator<? super T> comparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T, U> Comparator<T> comparing(
				Function<? super T, ? extends U> keyExtractor,
				Comparator<? super U> keyComparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T, U extends Comparable<? super U>> Comparator<T> comparing(
				Function<? super T, ? extends U> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingInt(
				ToIntFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingLong(
				ToLongFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingDouble(
				ToDoubleFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	public class OrdenaPaginas implements Comparator<Livro> {

		@Override
		public int compare(Livro primeiro, Livro segundo) {

			return Integer.toString( primeiro.getPaginas() ).compareTo(Integer.toString( segundo.getPaginas() ));
		}

		@Override
		public Comparator<Livro> reversed() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparing(Comparator<? super Livro> other) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <U> Comparator<Livro> thenComparing(
				Function<? super Livro, ? extends U> keyExtractor,
				Comparator<? super U> keyComparator) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <U extends Comparable<? super U>> Comparator<Livro> thenComparing(
				Function<? super Livro, ? extends U> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingInt(
				ToIntFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingLong(
				ToLongFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingDouble(
				ToDoubleFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> nullsFirst(
				Comparator<? super T> comparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> nullsLast(
				Comparator<? super T> comparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T, U> Comparator<T> comparing(
				Function<? super T, ? extends U> keyExtractor,
				Comparator<? super U> keyComparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T, U extends Comparable<? super U>> Comparator<T> comparing(
				Function<? super T, ? extends U> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingInt(
				ToIntFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingLong(
				ToLongFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingDouble(
				ToDoubleFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	public class OrdenaCategoria implements Comparator<Livro> {

		@Override
		public int compare(Livro primeiro, Livro segundo) {

			return primeiro.getCategoria().compareTo(segundo.getCategoria());
		}

		@Override
		public Comparator<Livro> reversed() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparing(Comparator<? super Livro> other) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <U> Comparator<Livro> thenComparing(
				Function<? super Livro, ? extends U> keyExtractor,
				Comparator<? super U> keyComparator) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <U extends Comparable<? super U>> Comparator<Livro> thenComparing(
				Function<? super Livro, ? extends U> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingInt(
				ToIntFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingLong(
				ToLongFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingDouble(
				ToDoubleFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> nullsFirst(
				Comparator<? super T> comparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> nullsLast(
				Comparator<? super T> comparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T, U> Comparator<T> comparing(
				Function<? super T, ? extends U> keyExtractor,
				Comparator<? super U> keyComparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T, U extends Comparable<? super U>> Comparator<T> comparing(
				Function<? super T, ? extends U> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingInt(
				ToIntFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingLong(
				ToLongFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingDouble(
				ToDoubleFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	public class OrdenaPrecoCusto implements Comparator<Livro> {

		@Override
		public int compare(Livro primeiro, Livro segundo) {

			return Double.toString( primeiro.getPrecoCusto() ).compareTo(Double.toString( segundo.getPrecoCusto() ));
		}

		@Override
		public Comparator<Livro> reversed() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparing(Comparator<? super Livro> other) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <U> Comparator<Livro> thenComparing(
				Function<? super Livro, ? extends U> keyExtractor,
				Comparator<? super U> keyComparator) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <U extends Comparable<? super U>> Comparator<Livro> thenComparing(
				Function<? super Livro, ? extends U> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingInt(
				ToIntFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingLong(
				ToLongFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingDouble(
				ToDoubleFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> nullsFirst(
				Comparator<? super T> comparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> nullsLast(
				Comparator<? super T> comparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T, U> Comparator<T> comparing(
				Function<? super T, ? extends U> keyExtractor,
				Comparator<? super U> keyComparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T, U extends Comparable<? super U>> Comparator<T> comparing(
				Function<? super T, ? extends U> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingInt(
				ToIntFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingLong(
				ToLongFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingDouble(
				ToDoubleFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	public class OrdenaPrecoVenda implements Comparator<Livro> {

		@Override
		public int compare(Livro primeiro, Livro segundo) {

			return Double.toString( primeiro.getPrecoVenda() ).compareTo(Double.toString( segundo.getPrecoVenda() ));
		}

		@Override
		public Comparator<Livro> reversed() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparing(Comparator<? super Livro> other) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <U> Comparator<Livro> thenComparing(
				Function<? super Livro, ? extends U> keyExtractor,
				Comparator<? super U> keyComparator) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <U extends Comparable<? super U>> Comparator<Livro> thenComparing(
				Function<? super Livro, ? extends U> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingInt(
				ToIntFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingLong(
				ToLongFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<Livro> thenComparingDouble(
				ToDoubleFunction<? super Livro> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> nullsFirst(
				Comparator<? super T> comparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> nullsLast(
				Comparator<? super T> comparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T, U> Comparator<T> comparing(
				Function<? super T, ? extends U> keyExtractor,
				Comparator<? super U> keyComparator) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T, U extends Comparable<? super U>> Comparator<T> comparing(
				Function<? super T, ? extends U> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingInt(
				ToIntFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingLong(
				ToLongFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}

		public <T> Comparator<T> comparingDouble(
				ToDoubleFunction<? super T> keyExtractor) {
			// TODO Auto-generated method stub
			return null;
		}
	}

	@Override
	public Comparator<Livro> reversed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Livro> thenComparing(Comparator<? super Livro> other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> Comparator<Livro> thenComparing(
			Function<? super Livro, ? extends U> keyExtractor,
			Comparator<? super U> keyComparator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U extends Comparable<? super U>> Comparator<Livro> thenComparing(
			Function<? super Livro, ? extends U> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Livro> thenComparingInt(
			ToIntFunction<? super Livro> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Livro> thenComparingLong(
			ToLongFunction<? super Livro> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Livro> thenComparingDouble(
			ToDoubleFunction<? super Livro> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> nullsFirst(Comparator<? super T> comparator) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> nullsLast(Comparator<? super T> comparator) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T, U> Comparator<T> comparing(
			Function<? super T, ? extends U> keyExtractor,
			Comparator<? super U> keyComparator) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
			Function<? super T, ? extends U> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> comparingInt(
			ToIntFunction<? super T> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> comparingLong(
			ToLongFunction<? super T> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <T> Comparator<T> comparingDouble(
			ToDoubleFunction<? super T> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}
}
