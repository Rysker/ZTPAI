import React, {useCallback, useState} from 'react';
import {PieChart, Pie, Cell, ResponsiveContainer} from 'recharts';

const countryMap = {
    "Russia" : "RU",
    "Germany" : "GER",
    "United States" : "USA",
    "United Kingdom" : "UK",
    "France" : "FRA",
    "Japan": "JPN",
    "Poland": "PL",
    "Sweden" : "SWE",
};

const countryColors = {
    "Russia": '#00C49F',
    "Germany": '#0088FE',
    "United States": '#FFBB28',
    "United Kingdom": '#FF8042',
    "France": '#8A2BE2',
    "Japan": '#FF6347',
    "Poland": '#20B2AA',
    "Sweden": '#FFD700',
};


const CollectionPieChart = ({ data }) =>
{
    const[animation, setAnimation] = useState(true);
    const RADIAN = Math.PI / 180;
    const duration = 1600;

    const renderCustomizedLabel = ({ cx, cy, midAngle, innerRadius, outerRadius, index }) =>
    {
        const radius = innerRadius + (outerRadius - innerRadius) * 0.5;
        const x = cx + radius * Math.cos(-midAngle * RADIAN);
        const y = cy + radius * Math.sin(-midAngle * RADIAN);

        const shortName = countryMap[data[index].country];

        return (
            <text x={x} y={y} fill="white" textAnchor={x > cx ? 'start' : 'end'} dominantBaseline="central">
                {`${shortName}`}
            </text>
        );
    };

    const onAnimationStart = useCallback(() =>
    {
        setTimeout(() =>
        {
            setAnimation(false)
        }, duration + 150)

    }, [])

    return (
        <ResponsiveContainer>
            <PieChart style={{ width: '100%', height: '100%' }}>
                <Pie
                    data={data}
                    dataKey="count"
                    nameKey="country"
                    cx="50%"
                    cy="50%"
                    labelLine={false}
                    label={renderCustomizedLabel}
                    outerRadius={'80%'}
                    fill="#8884d8"
                    stroke="none"
                    isAnimationActive={animation}
                    animationDuration={duration}
                    onAnimationStart={() => onAnimationStart}
                    onClick={() => {}}
                >
                    {data.map((entry, index) => (
                        <Cell
                            key={`cell-${entry.country}`}
                            fill={countryColors[entry.country]}
                        />
                    ))}
                </Pie>
            </PieChart>
        </ResponsiveContainer>
    );
};

export default CollectionPieChart;