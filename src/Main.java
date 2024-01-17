import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	static List<WiseSaying> wiseSayings = new ArrayList<>();
	static Map<String, String> params = new HashMap<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int lastId = 0;
		System.out.println("==명언 앱 실행==");

		while (true) {
			System.out.print("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("종료")) {
				break;
			}

			else if (cmd.equals("등록")) {
				int id = lastId + 1;
				System.out.print("명언: ");
				String content = sc.nextLine().trim();
				System.out.print("작가: ");
				String author = sc.nextLine().trim();
				System.out.println(id + "번 명언이 등록되었습니다.");
				WiseSaying wiseSaying = new WiseSaying(id, content, author);
				wiseSayings.add(wiseSaying);
				lastId++;

			} else if (cmd.equals("목록")) {
				System.out.println("번호  /  작가  /  명언");
				System.out.println("=".repeat(30));

				for (int i = wiseSayings.size() - 1; i >= 0; i--) {
					WiseSaying ws = wiseSayings.get(i);
					System.out.printf("%d  /  %s  / %s \n", ws.id, ws.author, ws.content);
				}
			} else if (cmd.startsWith("삭제")) {
				String cmdBits[] = cmd.split("\\?", 2);

				if (cmdBits.length == 1) {
					continue;
				}

				String paramBits[] = cmdBits[1].split("&");
				for (String paramStr : paramBits) {
					String[] paramStrBits = paramStr.split("=");
					if (paramStrBits.length == 1) {
						continue;
					}
					String key = paramStrBits[0];
					String value = paramStrBits[1];
					params.put(key, value);
				}

				int id = getIntParam("id", -1);
				if (id == -1) {
					System.out.println("정수로 입력해 주세요.");
					continue;
				}
				WiseSaying wiseSaying = findById(id);
				if (wiseSaying == null) {
					System.out.println(id + "번 명언은 존재하지 않습니다.");
					continue;
				}
				
				wiseSayings.remove(wiseSaying);
				
				System.out.println(id + "번 명언이 삭제되었습니다.");
			}else if(cmd.startsWith("수정")) {
				String cmdBits[] = cmd.split("\\?", 2);

				if (cmdBits.length == 1) {
					continue;
				}

				String paramBits[] = cmdBits[1].split("&");
				for (String paramStr : paramBits) {
					String[] paramStrBits = paramStr.split("=");
					if (paramStrBits.length == 1) {
						continue;
					}
					String key = paramStrBits[0];
					String value = paramStrBits[1];
					params.put(key, value);
				}

				int id = getIntParam("id", -1);
				if (id == -1) {
					System.out.println("정수로 입력해 주세요.");
					continue;
				}
				WiseSaying wiseSaying = findById(id);
				if (wiseSaying == null) {
					System.out.println(id + "번 명언은 존재하지 않습니다.");
					continue;
				}
				
				System.out.println("명언(기존): "+ wiseSaying.content);
				System.out.println("작가(기존): "+ wiseSaying.author);
				
				System.out.print("명언: ");
				String content = sc.nextLine().trim();
				System.out.print("작가: ");
				String author = sc.nextLine().trim();
				
				wiseSaying.setAuthor(author);
				wiseSaying.setContent(content);
				
				System.out.println(id + "번 명언이 수정되었습니다.");
				
			}else if(cmd.startsWith("상세보기")) {
				String cmdBits[] = cmd.split("\\?", 2);

				if (cmdBits.length == 1) {
					continue;
				}

				String paramBits[] = cmdBits[1].split("&");
				for (String paramStr : paramBits) {
					String[] paramStrBits = paramStr.split("=");
					if (paramStrBits.length == 1) {
						continue;
					}
					String key = paramStrBits[0];
					String value = paramStrBits[1];
					params.put(key, value);
				}

				int id = getIntParam("id", -1);
				if (id == -1) {
					System.out.println("정수로 입력해 주세요.");
					continue;
				}
				WiseSaying wiseSaying = findById(id);
				if (wiseSaying == null) {
					System.out.println(id + "번 명언은 존재하지 않습니다.");
					continue;
				}
				System.out.println("번호: "+ wiseSaying.id);
				System.out.println("작가: "+ wiseSaying.author);
				System.out.println("내용: "+ wiseSaying.content);
				
			}
			
		}

	}

	private static WiseSaying findById(int id) {
		for (WiseSaying wiseSaying : wiseSayings) {
			if (wiseSaying.id == id) {
				return wiseSaying;
			}
		}
		return null;
	}

	private static int getIntParam(String name, int defaultvalue) {
		try {
			return Integer.parseInt(getParam(name));
		} catch (NumberFormatException e) {

		}
		return defaultvalue;
	}

	private static String getParam(String name) {

		return params.get(name);
	}

}

class WiseSaying {
	int id;
	String content;
	String author;

	public WiseSaying(int id, String content, String author) {
		this.id = id;
		this.content = content;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
