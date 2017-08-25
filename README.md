# test-java

# Questão 4

Formalmente, um conjunto de processos está bloqueado se cada um dos processos está aguardando um evento que apenas outro processo neste mesmo conjunto pode causar.
Por exemplo, se temos os processos A, B, e C. Em determinado instante de execução o processo A está aguardando recursos serem liberados pelo processo B para executar determinada tarefa. O processo B por sua vez só pode liberar tais recursos quando o processo C concluir uma tarefa que por sua vez está aguardando lberação de recursos de A. temos então uma dependência ciclica e está configurado o deadlock.


# Questão 5

A API Stream do Java 8 otimiza a maneira como as coleções podem ser processadas, otimizando e resumindo expressivamente. Além disso, ela pode ser usada automaticamente e em paralelo em arquitetura de múltiplos núcleos.

