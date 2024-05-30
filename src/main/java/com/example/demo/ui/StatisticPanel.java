package com.example.demo.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import org.json.JSONObject;


public class StatisticPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private SwingController controller;
	
	
	public StatisticPanel(SwingController sc) {
		this();
		controller = sc;
		setGraph();
	}
	
	public StatisticPanel() {
		setBorder(new LineBorder(new Color(0, 128, 255), 2, true));
		setLayout(new GridLayout(2, 2));
		
	}
	
	private HashMap<String, Integer> makeGraphData(JSONObject obj){
		HashMap<String, Integer> data = new HashMap<>();
		
		Iterator<String> keys = obj.keys();
		
		while(keys.hasNext()) {
			String key = keys.next();
			data.put(key, (int) obj.get(key));
		}
		
		return data;
	}
	
	public void setGraph(){
		this.removeAll();
		
		String statusString = controller.getIssueStatics(0);
		String typeString = controller.getIssueStatics(1);
		String priorityString = controller.getIssueStatics(2);
		String trendString = controller.getIssueTrend();
		
		JSONObject trend = new JSONObject(trendString);
		JSONObject status = new JSONObject(statusString);
		JSONObject priority = new JSONObject(priorityString);
		JSONObject type = new JSONObject(typeString);
		
		Map<String, Integer> trendData = makeGraphData(trend);
		Map<String, Integer> statusData = makeGraphData(status);
		Map<String, Integer> priorityData = makeGraphData(priority);
		Map<String, Integer> typeData = makeGraphData(type);
		
		GraphPanel trendPanel = new GraphPanel(trendData);
		GraphPanel statusPanel = new GraphPanel(statusData);
		GraphPanel priorityPanel = new GraphPanel(priorityData);
		GraphPanel typePanel = new GraphPanel(typeData);
		
		add(trendPanel);
		add(statusPanel);
		add(priorityPanel);
		add(typePanel);
	}
	
	private class GraphPanel extends JPanel{
		private Map<String, Integer> data;

	    public GraphPanel(Map<String, Integer> data) {
	        this.data = data;
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);

	        // 막대의 최대 높이 및 기타 설정
	        int maxBarHeight = getHeight() - 70;
	        int barWidth = 40;
	        int gap = 30;
	        int x = 50;
	        
	        int maxValue = data.values().stream().max(Integer::compareTo).orElse(1);

	        // 그래프 그리기
	        for (Map.Entry<String, Integer> entry : data.entrySet()) {
	            int barHeight = (int) ((double) entry.getValue() / maxValue * maxBarHeight);
	            g.setColor(Color.BLUE);
	            g.fillRect(x, getHeight() - barHeight - 50, barWidth, barHeight);
	            g.setColor(Color.BLACK);
	            g.drawRect(x, getHeight() - barHeight - 50, barWidth, barHeight);
	            g.drawString(entry.getKey(), x, getHeight() - 30);
	            x += barWidth + gap;
	        }
	    }
	}

}
