// my code 57% 获取前10份把所有的信息都放入一个列表进行排序，没有必要
class Twitter {

    private int date = 0;
    private Map<Integer, List<int[]>> userMsgMap;
    private Map<Integer, Set<Integer>> followMap;
    /** Initialize your data structure here. */
    public Twitter() {
        userMsgMap = new HashMap<>();
        followMap = new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        int[] msg = new int[2];
        msg[0] = date++;
        msg[1] = tweetId;
        List<int[]> msgList = userMsgMap.get(userId);
        if (msgList == null) {
            msgList = new ArrayList<>();
            userMsgMap.put(userId, msgList);
        }
        msgList.add(msg);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<int[]> allMsgs = new ArrayList<>();
        List<int[]> myMsgs = userMsgMap.get(userId);
        if (myMsgs != null) {
            allMsgs.addAll(myMsgs);
        }
        Set<Integer> followees = followMap.get(userId);
        if (followees != null) {
            for (Integer id : followees) {
                List<int[]> msg = userMsgMap.get(id);
                if (msg != null) {
                    allMsgs.addAll(msg);
                }
            }
        }
        
        allMsgs.sort((o1, o2) -> o2[0] - o1[0]);
        List<Integer> result= new ArrayList<>();
        int len = Math.min(10, allMsgs.size());
        for (int i = 0; i < len; ++i) {
            result.add(allMsgs.get(i)[1]);
        }
        return result;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        Set<Integer> followees = followMap.get(followerId);
        if (followees == null) {
            followees = new HashSet<>();
            followMap.put(followerId, followees);
        }
        followees.add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        Set<Integer> followees = followMap.get(followerId);
        if (followees != null) {
            followees.remove(followeeId);
        }
    }
}


// discuss 两个数据结构，信息为链表的形式，获取前10个信息利用优先队列（理论效率较高，可能因为实例化了对象，运行速度还没有我的快）
public class Twitter {
	private static int timeStamp=0;

	// easy to find if user exist
	private Map<Integer, User> userMap;

	// Tweet link to next Tweet so that we can save a lot of time
	// when we execute getNewsFeed(userId)
	private class Tweet{
		public int id;
		public int time;
		public Tweet next;

		public Tweet(int id){
			this.id = id;
			time = timeStamp++;
			next=null;
		}
	}


	// OO design so User can follow, unfollow and post itself
	public class User{
		public int id;
		public Set<Integer> followed;
		public Tweet tweet_head;

		public User(int id){
			this.id=id;
			followed = new HashSet<>();
			follow(id); // first follow itself
			tweet_head = null;
		}

		public void follow(int id){
			followed.add(id);
		}

		public void unfollow(int id){
			followed.remove(id);
		}


		// everytime user post a new tweet, add it to the head of tweet list.
		public void post(int id){
			Tweet t = new Tweet(id);
			t.next=tweet_head;
			tweet_head=t;
		}
	}




	/** Initialize your data structure here. */
	public Twitter() {
		userMap = new HashMap<Integer, User>();
	}

	/** Compose a new tweet. */
	public void postTweet(int userId, int tweetId) {
		if(!userMap.containsKey(userId)){
			User u = new User(userId);
			userMap.put(userId, u);
		}
		userMap.get(userId).post(tweetId);

	}



	// Best part of this.
	// first get all tweets lists from one user including itself and all people it followed.
	// Second add all heads into a max heap. Every time we poll a tweet with 
	// largest time stamp from the heap, then we add its next tweet into the heap.
	// So after adding all heads we only need to add 9 tweets at most into this 
	// heap before we get the 10 most recent tweet.
  // 获取前10份信息
	public List<Integer> getNewsFeed(int userId) {
		List<Integer> res = new LinkedList<>();

		if(!userMap.containsKey(userId))   return res;

		Set<Integer> users = userMap.get(userId).followed;
		PriorityQueue<Tweet> q = new PriorityQueue<Tweet>(users.size(), (a,b)->(b.time-a.time));
		for(int user: users){
			Tweet t = userMap.get(user).tweet_head;
			// very imporant! If we add null to the head we are screwed.
			if(t!=null){
				q.add(t); // 先把每个跟随者最大的信息节点放进优先队列
			}
		}
		int n=0;
		while(!q.isEmpty() && n<10){
		  Tweet t = q.poll();
		  res.add(t.id); // 添加每次获取最近发布的信息
		  n++;
		  if(t.next!=null)
			q.add(t.next); // 将最近发布信息的后一个节点信息加入优先队列
		}

		return res;

	}

	/** Follower follows a followee. If the operation is invalid, it should be a no-op. */
	public void follow(int followerId, int followeeId) {
		if(!userMap.containsKey(followerId)){
			User u = new User(followerId);
			userMap.put(followerId, u);
		}
		if(!userMap.containsKey(followeeId)){
			User u = new User(followeeId);
			userMap.put(followeeId, u);
		}
		userMap.get(followerId).follow(followeeId);
	}

	/** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
	public void unfollow(int followerId, int followeeId) {
		if(!userMap.containsKey(followerId) || followerId==followeeId)
			return;
		userMap.get(followerId).unfollow(followeeId);
	}
}


// 排行榜第一的获取信息代码，同样利用优先队列（感觉没有discuss的好，万一后续信息节点都比第一次插入优先队列的大，将会频繁进行删除插入操作）
public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b) -> a.postTime - b.postTime);
        
        if( !followees.containsKey(userId) ) return new LinkedList<>();
        
        for( int followeeId : followees.get(userId) ) {
            if( !feed.containsKey(followeeId) ) continue;
            for( Tweet tweet : feed.get(followeeId) ) {
                if( pq.size() < feedSize ) { // 如果优先队列没有填充满，则填充
                    pq.offer(tweet);
                } else { // 如果优先队列满了，需要将插入的节点和优先队列最后一个进行比较
                // 如果时间小，说明时隔远，不需要插入，退出当前用户的信息循环（因为第一个信息节点的时间是最大的，后续节点不必比较）
                    if( tweet.postTime <= pq.peek().postTime ) break; 
                    else { // 如果时间大，则删除最后一个，添加当前信息节点
                         pq.poll();
                        pq.offer(tweet);
                    }
                }
            }
        }
        List<Integer> res = new LinkedList<>();
        
        while( !pq.isEmpty() ) {
            res.add(0, pq.poll().tweetId);
        }
        return res;
    }
