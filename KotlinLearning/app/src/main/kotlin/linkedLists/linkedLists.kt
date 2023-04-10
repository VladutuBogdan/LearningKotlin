fun main() {

    // Example 1
    class Node<T>(var value: T, var next: Node<T>? = null) {
        override fun toString(): String {
            return if (next != null) "${value} -> ${next}" else value.toString();
        }
    }

    val firstNode = Node(1);
    val secondNode = Node(2);
    val thirdNode = Node(3);

    firstNode.next = secondNode;
    secondNode.next = thirdNode;

    println(firstNode); // 1 -> 2 -> 3

    class LinkedList<T> {
        private var head: Node<T>? = null;
        private var tail: Node<T>? = null;
        private var size = 0;

        fun isEmpty(): Boolean {
            return size == 0
        }

        fun push(value: T) {
            head = Node(value = value, next = head)
            if (tail == null) {
                tail = head
            }
            size++
        }

        fun pop() {
            if (isEmpty()) return;
            size--;
            if (size == 0) {
                head = null
                tail = null
                return;
            }
            head = head?.next;
        }

        fun removeLast() {
            if (isEmpty()) return;
            size--;
            if (size == 0) {
                head = null
                tail = null
                return;
            }

            var currentNode = head;
            var prevNode = currentNode;
            while (currentNode?.next != null) {
                prevNode = currentNode;
                currentNode = currentNode?.next;
            }

            prevNode?.next = null;
        }

        fun append(value: T) {
            if (isEmpty()) {
                push(value);
                return;
            }
            tail?.next = Node(value);
            tail = tail?.next;
            size++;
        }

        fun nodeAt(index: Int): Node<T>? {
            var iterator = 0;
            var currentNode = head;

            while (iterator < index && currentNode?.next != null) {
                currentNode = currentNode?.next
                iterator++
            }
            return currentNode
        }

        fun insert(value: T, position: Int) {
            var nodeAt = nodeAt(position);

            if (tail == nodeAt) {
                append(value);
                return;
            }

            val newNode = Node(value = value, next = nodeAt?.next)

            nodeAt?.next = newNode
            size++
        }

        override fun toString(): String {
            if (isEmpty()) {
                return "Empty list"
            } else {
                return head.toString()
            }
        }
    }

    val list = LinkedList<Int>()
    list.push(3)
    list.push(2)
    list.push(1)
    list.append(1)
    list.append(2)
    list.append(3)
    list.insert(-1, 3)
    list.pop()
    list.removeLast()
    println(list)
}