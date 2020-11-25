interface ResourcedTest {
    private fun getResource(name: String) =
        this::class.java.getResource(if (name.startsWith("/")) name else "/$name").readText()
    infix fun <T> Problem<T>.withInput(resourceFileName: String) = this.go(getResource(resourceFileName))
    infix fun Problem<Boolean>.passWithInput(resourceFileName: String) = this.withInput(resourceFileName)
    infix fun Problem<Boolean>.failWithInput(resourceFileName: String) = !this.withInput(resourceFileName)
}
