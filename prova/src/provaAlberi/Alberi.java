package provaAlberi;

import java.util.List;
import java.util.function.Function;


import javafx.scene.control.*;

public class Alberi {
	
	private TreeItem<String> root;
	private TreeItem<String> animali;
	private TreeItem<String> vegetali;
	
	
	public Alberi(TreeItem<String> root, TreeItem<String> animali, TreeItem<String> vegetali) {
		this.root = root;
		this.animali = animali;
		this.vegetali = vegetali;
		root.getChildren().add(animali);
		root.getChildren().add(vegetali);
		animali.getChildren().add(new TreeItem<>("Pesci"));
		animali.getChildren().add(new TreeItem<>("Mammiferi"));
		vegetali.getChildren().add(new TreeItem<>("Graminacee"));
		vegetali.getChildren().add(new TreeItem<>("Betullacee"));
	}
	
	public TreeItem<String> getRoot() {
		return root;
	}

	public TreeItem<String> getAnimali() {
		return animali;
	}

	public TreeItem<String> getVegetali() {
		return vegetali;
	}
	
	private static <T> void preorder(TreeItem<T> root, StringBuilder sb, String separator) {
		if (root != null) {
			sb.append(root.getValue());
			List<TreeItem<T>> children = root.getChildren();
			if (children != null) {
				for (TreeItem<T> child : children) {
					sb.append(separator);
					preorder(child, sb, separator);
				}
			}
		}
	}
	
	private static <T> void inorder(TreeItem<T> root, StringBuilder sb, String separator) {
		if (root != null) {
			List<TreeItem<T>> children = root.getChildren();
			if (children != null && children.size() > 2) 
				throw new IllegalArgumentException("Not binary!");
			if (children != null && children.size() > 0) 
				inorder(children.get(0), sb, separator);
			sb.append(root.getValue()+ separator);
			if (children != null && children.size() > 1) 
				inorder(children.get(1), sb, separator);
		}
	}
	
	private static <T> long count(TreeItem<T> root) {
		if (root==null) 
			return 0;
		int sum = 1;
		List<TreeItem<T>> children = root.getChildren();
		if (children != null) 
			for (TreeItem<T> child : children) 
				sum += count(child); // chiamata ricorsiva
	
		return sum;
	}
	
	public static <T> long countIf(TreeItem<T> root) {
		if (root == null)
			return 0;
		Function<TreeItem<T>,Integer> filter = (TreeItem<T> n) -> ((String) n.getValue()).toUpperCase().indexOf('A') >= 0 ? 1 : 0;
		int sum = filter.apply(root);
		List<TreeItem<T>> children = root.getChildren();
		if (children != null)
			for (TreeItem<T> child : children)
				sum += countIf(child); // chiamata ricorsiva
		
		return sum;
	}

	
	
	public static void main(String args[]) {
		TreeItem<String> root = new TreeItem<>("Esseri viventi");
		TreeItem<String> animali = new TreeItem<>("Animali");
		TreeItem<String> vegetali = new TreeItem<>("Vegetali");
		
		Alberi albero = new Alberi(root, animali, vegetali);
		
		StringBuilder sb = new StringBuilder();
		
		inorder(albero.getRoot(), sb, ", ");
		System.out.println("In order: " + sb);
		
		sb.delete(0, sb.length());
		
		preorder(albero.getRoot(), sb, "; ");
		System.out.println("Pre order: " + sb);
		
		System.out.println("Count: " + count(albero.getVegetali()));
		
		System.out.println("nodes with 'A': " + countIf(albero.getRoot()));
	}

}


