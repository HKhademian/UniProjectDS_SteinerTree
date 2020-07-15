import java.util.*

fun <E> Collection<E>.randomOrNull(random: Random = Random()): E? =
	if (size > 0) elementAtOrNull(random.nextInt(size)) else null
