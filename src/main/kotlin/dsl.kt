import java.util.*

fun <E> List<E>.randomOrNull(random: Random = Random()): E? =
	if (size > 0) get(random.nextInt(size)) else null
