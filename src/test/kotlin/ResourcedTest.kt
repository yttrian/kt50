interface ResourcedTest {
    private fun getResource(name: String) =
        this::class.java.getResource(if (name.startsWith("/")) name else "/$name").readText()
    infix fun <T> Problem<T>.withInput(resourceFileName: String) = this.go(getResource(resourceFileName))
}
