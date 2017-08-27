package nolambda.androidstarter.di.helpers

interface HasComponent<T> {
    fun getComponent(): T
}
