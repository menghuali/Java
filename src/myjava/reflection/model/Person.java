package myjava.reflection.model;

import myjava.reflection.annotation.Column;
import myjava.reflection.annotation.PrimaryKey;

public class Person {

	@PrimaryKey(name = "ID")
	private long id;

	@Column(name = "NAME")
	private String name;

	@Column
	private int age;

	public Person() {
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public static Person of(String name, int age) {
		return new Person(name, age);
	}

	public String getName() {
		System.out.println("Invoke getName()");
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
