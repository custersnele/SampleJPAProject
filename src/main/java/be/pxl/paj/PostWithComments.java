package be.pxl.paj;

import be.pxl.paj.domain.posts.Post;
import be.pxl.paj.domain.posts.PostComment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class PostWithComments {

	public static void main(String[] args) throws Exception {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musicdb_pu");
		EntityManager em = emf.createEntityManager();

		// cleanup old data.
		em.getTransaction().begin();
		Query delete = em.createQuery("DELETE FROM PostComment");
		delete.executeUpdate();
		delete = em.createQuery("DELETE FROM Post");
		delete.executeUpdate();
		em.getTransaction().commit();
		em.clear();
		// end of cleanup
		em.getTransaction().begin();

		Post post1 = new Post("PXL'er Ward Lemmelijn kroont zich tot wereldkampioen indoorroeien.");
		em.persist(post1);
		Post post2 = new Post("Jaarlijkse Hackaton gaat online.");
		em.persist(post2);
		Post post3 = new Post("PXL naar finale in Cybersecurity challenge.");
		em.persist(post3);
		PostComment comment1 = new PostComment(post1, "Schitterend ***");
		PostComment comment2 = new PostComment(post1, "Proficiat!");
		PostComment comment3 = new PostComment(post2, "Jammer maar gelukkig gaat het door.");
		PostComment comment4 = new PostComment(post2, "Ik ben zeker van de partij!");
		PostComment comment5 = new PostComment(post3, "Ik hou van uitdagingen!");

		em.persist(comment1);
		em.persist(comment2);
		em.persist(comment3);
		em.persist(comment4);
		em.persist(comment5);

		em.getTransaction().commit();
		em.clear();

		//List<PostComment> comments = em.createQuery("select pc from PostComment pc", PostComment.class).getResultList();
		List<PostComment> comments = em.createQuery("select pc from PostComment pc join fetch pc.post p", PostComment.class).getResultList();
		for (PostComment comment : comments) {
			System.out.println(comment.getReview() + " on " + comment.getPost().getTitle());
		}


		em.close();
		emf.close();
	}

}
