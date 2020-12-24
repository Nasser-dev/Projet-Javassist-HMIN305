import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

class Test {
	public void myMethod() {
        System.out.print("Hello world");
	}
}

public class TestJavassist {
	public static void main(String[] args) throws Exception {
		// Creation d'un pool de classes par javassist. Le pool peut etre vu comme un cache.
		ClassPool pool = ClassPool.getDefault();

		// Extraction de notre classe Test.
		CtClass ctClass = pool.get("Test");

		// Recherche de la methode a modifier
		CtMethod ctMethod = ctClass.getDeclaredMethod("myMethod");

		// Insertion a chaud de code Java dans la methode ctMethode d'une classe ctClass. 
		// Notez que le code est un bloc entre { .. }
		ctMethod.insertAfter("{ System.out.println(\" you are in myMethod\"); }");

		// On transforme le CtClass Javassist en Class Java classique et on fabrique une nouvelle instance
		Class<?> c = ctClass.toClass();
		@SuppressWarnings("deprecation")
		Test test = (Test) c.newInstance();

		// Appel de la methode myMethod de la classe Test
		test.myMethod();
	}
}